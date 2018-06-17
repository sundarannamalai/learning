package io.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
 * Time: 1:01 PM
 */
@Configuration
@EnableBatchProcessing
public class FlowFirstJobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step myStep() {
    return stepBuilderFactory.get("myStep")
        .tasklet((contribution, chunkContext) -> {
              System.out.println("==> This is step 3.");
              return RepeatStatus.FINISHED;
            }
        ).build();
  }

  @Bean
  public Job flowFirstJob(Flow flow) {
    return jobBuilderFactory.get("flowFirstJob")
        .start(flow)
        .on("COMPLETED").to(myStep())
        .end()
        .build();
  }

}