package io.spring.retry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

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

  @Value("classpath:/data/mlb_players.csv")
  private Resource dataFile;

  @Bean
  public Job parallelJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) throws Exception {
    return jobBuilderFactory.get("retryJob")
        .start(step1(stepBuilderFactory))
        .build();
  }

  @Bean
  public ItemReader<MLBPlayer> statefulReader() throws Exception {
    FlatFileItemReader<MLBPlayer> csvReader = new FlatFileItemReader<>();
    csvReader.setLinesToSkip(1); //skip headers
    csvReader.setResource(dataFile);

    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setNames("Name", "Team", "Position", "Height", "Weight", "Age");
    tokenizer.setQuoteCharacter('\"');
    tokenizer.afterPropertiesSet();

    DefaultLineMapper<MLBPlayer> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(tokenizer);
    lineMapper.setFieldSetMapper((fieldSet) -> {
      MLBPlayer player = new MLBPlayer();
      player.setName(fieldSet.readString("Name"));
      player.setTeam(fieldSet.readString("Team"));
      player.setPosition(fieldSet.readString("Position"));
      player.setHeight(fieldSet.readInt("Height", -1));
      player.setWeight(fieldSet.readInt("Weight", -1));
      player.setAge(fieldSet.readDouble("Age"));
      return player;
    });
    lineMapper.afterPropertiesSet();
    csvReader.setLineMapper(lineMapper);
    return csvReader;
  }

  @Bean
  public ItemProcessor<MLBPlayer, MLBPlayer> itemProcessor() {
    return new CustomProcessor();
  }

  @Bean
  public ItemWriter<MLBPlayer> jsonWriter() throws IOException {
    FlatFileItemWriter<MLBPlayer> writer = new FlatFileItemWriter<>();
    File tmpFile = File.createTempFile("mlb_player", "result");
    System.out.println("Out path: " + tmpFile.getAbsolutePath());
    writer.setResource(new FileSystemResource(tmpFile));
    writer.setLineAggregator((item) -> {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        return objectMapper.writeValueAsString(item);
      } catch (JsonProcessingException e) {
        return null;
      }
    });
    writer.setAppendAllowed(true);
    return writer;
  }

  @Bean
  public Step step1(StepBuilderFactory stepBuilderFactory) throws Exception {
    return stepBuilderFactory.get("retryStep")
        .<MLBPlayer, MLBPlayer>chunk(10)
        .reader(statefulReader())
        .processor(itemProcessor())
        .writer(new CustomWriter())
        .faultTolerant()
        .retry(CustomWriterException.class)
        .retry(CustomProcessingException.class)
        .retryLimit(10)
        .build();
  }

}
