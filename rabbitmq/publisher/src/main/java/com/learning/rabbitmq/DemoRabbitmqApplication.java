package com.learning.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoRabbitmqApplication implements CommandLineRunner {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public static void main(String[] args) {
    SpringApplication.run(DemoRabbitmqApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    SimpleMessage simpleMessage = new SimpleMessage("FirstMessage", "Sample message description");
    rabbitTemplate.convertAndSend("Hello! This is demo application");
    rabbitTemplate.convertAndSend("TestDirectExchange", "TestRouting", simpleMessage);
    rabbitTemplate.convertAndSend("TestDirectExchange", "TestRouting", "Hello! This is demo application");
  }
}
