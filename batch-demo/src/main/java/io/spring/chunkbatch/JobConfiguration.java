package io.spring.chunkbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 13/06/18
 * Time: 1:01 PM
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public ItemReader<String> reader() {
    return new ListItemReader<>(Arrays.asList("first", "second", "thrid"));
  }

  @Bean
  public ItemWriter<String> writer() {
    return (items -> System.out.println("Writing items = " + items.toString()));
  }

  @Bean
  public Step helloStep1() {
    return stepBuilderFactory.get("helloStep1")
        .<String, String> chunk(2)
        .reader(reader())
        .writer(writer())
        .listener(new ChunkListener() {
          @Override
          public void beforeChunk(ChunkContext context) {
            System.out.println("<<< Before Chunk");
          }

          @Override
          public void afterChunk(ChunkContext context) {
            System.out.println(">>> After Chunk");
          }

          @Override
          public void afterChunkError(ChunkContext context) {

          }
        })
        .build();
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
        .next(helloStep2())
        .next(helloStep3())
        .listener(new JobExecutionListener() {

          @Override
          public void beforeJob(JobExecution jobExecution) {
            System.out.println("<< Before Job " + jobExecution.getJobInstance().getJobName());
          }

          @Override
          public void afterJob(JobExecution jobExecution) {
            System.out.println("<< After Job " + jobExecution.getJobInstance().getJobName() + " Exit Status = " + jobExecution.getExitStatus());
          }
        })
        .build();
  }

}