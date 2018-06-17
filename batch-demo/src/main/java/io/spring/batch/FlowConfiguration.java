package io.spring.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 13/06/18
 * Time: 4:55 PM
 */
@Configuration
public class FlowConfiguration {

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .tasklet((contribution, chunkContext) -> {
              System.out.println("==> This is step 1 in foo.");
              return RepeatStatus.FINISHED;
            }
        ).build();
  }

  @Bean
  public Step step2() {
    return stepBuilderFactory.get("step2")
        .tasklet((contribution, chunkContext) -> {
              System.out.println("==> This is step 2 in foo.");
              return RepeatStatus.FINISHED;
            }
        ).build();
  }

  @Bean
  public Flow foo() {
    FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foo");
    flowBuilder.start(step1())
        .next(step2())
        .end();
    return flowBuilder.build();
  }

}
