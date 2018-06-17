package io.spring.parallel;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 13/06/18
 * Time: 9:53 PM
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Tasklet countingTasklet() {
    return new CountingTasklet();
  }

  @Bean
  public Flow flow1() {
    FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow1");
    flowBuilder.start(stepBuilderFactory.get("helloStep1")
        .tasklet(countingTasklet()).build())
        .end();
    return flowBuilder.build();
  }

  @Bean
  public Flow flow2() {
    FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow2");
    flowBuilder
        .start(stepBuilderFactory.get("helloStep2")
          .tasklet(countingTasklet()).build())
        .next(stepBuilderFactory.get("helloStep3")
          .tasklet(countingTasklet()).build())
        .end();
    return flowBuilder.build();
  }

  @Bean
  public Job parallelJob() {
    return jobBuilderFactory.get("parallelJob")
        .start(flow1())
        .split(new SimpleAsyncTaskExecutor()).add(flow2())
        .end()
        .build();
  }


  private static class CountingTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
      System.out.println(String.format("%s has been executed by thread %s", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
      return RepeatStatus.FINISHED;
    }
  }
}
