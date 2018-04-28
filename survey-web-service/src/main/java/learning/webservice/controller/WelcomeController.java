package learning.webservice.controller;

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

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome!";
  }
}
