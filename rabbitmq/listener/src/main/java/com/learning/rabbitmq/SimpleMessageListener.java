package com.learning.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 15/09/18
 * Time: 8:18 PM
 */
public class SimpleMessageListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    System.out.println("message = [" + new String(message.getBody()) + "]");
  }
}
