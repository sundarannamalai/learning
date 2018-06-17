package io.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 13/06/18
 * Time: 1:01 PM
 */
@Configuration
@EnableBatchProcessing
public class HelloWorldJobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;


  @Bean
  public Step helloStep1() {
    return stepBuilderFactory.get("step1")
        .tasklet((contribution, chunkContext) -> {
            System.out.println("==> This is step 1.");
            return RepeatStatus.FINISHED;
          }
        ).build();
  }

  @Bean
  public Step helloStep2() {
    return stepBuilderFactory.get("step2")
        .tasklet((contribution, chunkContext) -> {
              System.out.println("==> This is step 2.");
              return RepeatStatus.FINISHED;
            }
        ).build();
  }

  @Bean
  public Step helloStep3() {
    return stepBuilderFactory.get("step3")
        .tasklet((contribution, chunkContext) -> {
              System.out.println("==> This is step 3.");
              return RepeatStatus.FINISHED;
            }
        ).build();
  }

  @Bean
  public Job helloWorldJob() {
    return jobBuilderFactory.get("helloWorldJob")
        .start(helloStep1())
        .on("COMPLETED").to(helloStep2())
        .next(helloStep3())
        .end()
        .build();
  }

}