package com.learning.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 15/09/18
 * Time: 8:19 PM
 */
@Configuration
public class RabbitMQConfig {

  private static final String QUEUE_NAME = "MyQueue";

  @Bean
  Queue myQueue() {
    return new Queue(QUEUE_NAME, true);
  }

  @Bean
  CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
    cachingConnectionFactory.setUsername("guest");
    cachingConnectionFactory.setPassword("guest");
    return cachingConnectionFactory;
  }

  @Bean
  SimpleMessageListenerContainer messageListenerContainer() {
    SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
    messageListenerContainer.setConnectionFactory(connectionFactory());
    messageListenerContainer.setQueues(myQueue());
    messageListenerContainer.setMessageListener(new SimpleMessageListener());
    return messageListenerContainer;
  }
}
