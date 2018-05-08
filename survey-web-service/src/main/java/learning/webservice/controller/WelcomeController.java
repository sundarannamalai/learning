package learning.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 28/04/18
 * Time: 8:29 PM
 */
@RestController
@ApiIgnore
public class WelcomeController {

  @Value("${welcome.message}")
  private String message;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/welcome")
  public String welcome() {
    return message;
  }

  @GetMapping("/say-goodmorning")
  public String sayGoodMorningInternationalized() {
    return messageSource.getMessage("goodmorning.welcome.message", null, LocaleContextHolder.getLocale());
  }

  @Profile("dev")
  @Bean
  private String dummy() {
    return "something else";
  }
}
