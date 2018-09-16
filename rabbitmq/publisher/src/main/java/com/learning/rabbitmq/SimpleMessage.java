package com.learning.rabbitmq;

import java.io.Serializable;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 15/09/18
 * Time: 7:37 PM
 */
public class SimpleMessage implements Serializable {

  private String name;
  private String description;

  public SimpleMessage(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
