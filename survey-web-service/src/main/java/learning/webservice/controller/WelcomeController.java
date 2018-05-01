package learning.webservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 28/04/18
 * Time: 8:29 PM
 */
@RestController
public class WelcomeController {

  @Value("${welcome.message}")
  private String message;

  @GetMapping("/welcome")
  public String welcome() {
    return message;
  }

  @Profile("dev")
  @Bean
  private String dummy() {
    return "something else";
  }
}
