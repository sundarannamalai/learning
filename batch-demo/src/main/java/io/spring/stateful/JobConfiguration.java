package io.spring.stateful;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 16/06/18
 * Time: 2:42 PM
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

  //TODO: Ensure Batch database is not cleaned

  @Bean
  public Job parallelJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    return jobBuilderFactory.get("statefulJob")
        .start(step1(stepBuilderFactory))
        .build();
  }

  @Bean
  public ItemReader statefulReader() {
    List<Integer> items = new ArrayList<>();
    for(int i = 1;i < 50;i++) {
      items.add(i);
    }
    return new StatefulItemReader(items);
  }

  private class StatefulItemReader implements ItemStream, ItemReader<Integer> {

    private final List<Integer> items;
    private int curIndex;
    private boolean restart;

    StatefulItemReader(List<Integer> items) {
      this.items = items;
      this.curIndex = 0;
      this.restart = false;
    }

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
      Integer item = null;
      if(this.curIndex < items.size()) {
        item = this.items.get(curIndex++);
      }
      if(this.curIndex == 42 && !restart) {
        throw new RuntimeException("This is intentional. Let us see what happens after restart");
      }
      return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
      if(executionContext.containsKey("curIndex")) {
        this.curIndex = executionContext.getInt("curIndex");
        this.restart = true;
      } else {
        this.curIndex = 0;
        executionContext.put("curIndex", this.curIndex);
      }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
      executionContext.putInt("curIndex", this.curIndex);
    }

    @Override
    public void close() throws ItemStreamException {

    }
  }

  @Bean
  public Step step1(StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get("step1")
        .<String, String>chunk(5)
        .reader(statefulReader())
        .writer((list) -> {
          System.out.println("cur Item list :" + list);
        }).build();
  }

}
