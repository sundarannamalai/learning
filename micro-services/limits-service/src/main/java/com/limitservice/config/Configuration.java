package com.limitservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 10/05/18
 * Time: 11:34 PM
 */
@Component
@ConfigurationProperties(prefix = "limits-service")
public class Configuration {
  private int max;
  private int min;

  public Configuration() {
  }

  public Configuration(int max, int min) {
    this.max = max;
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }
}
