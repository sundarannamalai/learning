package io.spring.reader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
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


  @Bean
  public Job parallelJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    return jobBuilderFactory.get("parallelJob")
        .start(step1(stepBuilderFactory))
        .build();
  }

  @Bean
  public ItemReader statelessItemReader() {
    List<String> items = new ArrayList<>();
    items.add("ABC");
    items.add("Foo");
    items.add("Bar");
    return new StatelessItemReader(items);
  }

  private class StatelessItemReader implements ItemReader<String> {

    private final Iterator<String> iterator;

    public StatelessItemReader(List<String> items) {
      this.iterator = items.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
      if(this.iterator.hasNext()) {
        return this.iterator.next();
      } else {
        return null;
      }
    }
  }

  @Bean
  public Step step1(StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get("step1")
        .<String, String>chunk(2)
        .reader(statelessItemReader())
        .writer((list) -> {
          System.out.println("cur Item :" + list);
        }).build();
  }

}
