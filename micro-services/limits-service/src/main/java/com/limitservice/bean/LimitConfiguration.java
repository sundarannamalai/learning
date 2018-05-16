package com.limitservice.bean;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 10/05/18
 * Time: 11:25 PM
 */
public class LimitConfiguration {
  private int max;
  private int min;

  protected LimitConfiguration() {
    //default constructor
  }

  public LimitConfiguration(int max, int min) {
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
