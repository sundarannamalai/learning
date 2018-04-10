package learning.springboot.web.controller;

import learning.springboot.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 07/04/18
 * Time: 5:55 PM
 */
@Controller
@SessionAttributes("name")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String showLogin(ModelMap modelMap) {
    return "login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String showWelcomePage(ModelMap modelMap, @RequestParam String name, @RequestParam String password) {
    if(loginService.validate(name, password)) {
      modelMap.put("name", name);
      return "welcome";
    } else {
      modelMap.put("error", "Your username or password is incorrect.");
      return "login";
    }
  }
}
