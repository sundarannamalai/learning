package io.spring.retry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 17/06/18
 * Time: 4:13 PM
 */
public class CustomProcessor implements ItemProcessor<MLBPlayer, MLBPlayer> {

  private static int attempt = 0;

  @Override
  public MLBPlayer process(MLBPlayer item) throws Exception {
    System.out.println("Trying to process " + item.getName());
    if(StringUtils.equalsIgnoreCase("Mike Redmond", item.getName()) && attempt <= 5) {
      System.out.println("==> \n\nRetry attempt. " + (++attempt));
      throw new CustomProcessingException("I can't process " + item.getName() + " now");
    }
    item.setName(StringUtils.upperCase(item.getName()));
    return item;
  }
}
