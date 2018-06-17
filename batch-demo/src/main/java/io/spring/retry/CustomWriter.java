package io.spring.retry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 17/06/18
 * Time: 4:21 PM
 */
public class CustomWriter implements ItemWriter<MLBPlayer> {

  private static int attempt = 0;
  @Override
  public void write(List<? extends MLBPlayer> items) throws Exception {
    items.forEach((player) -> {
      System.out.println("Trying to write player info : " + player.getName());
      if(StringUtils.equalsIgnoreCase(player.getName(), "Kevin Correia") && (++attempt) <= 5) {
        System.out.println("Exception processing " + player.getName() + ". Retrying attempt " + attempt);
        throw new CustomWriterException("Unable to write " + player.getName());
      }
    });
  }
}
