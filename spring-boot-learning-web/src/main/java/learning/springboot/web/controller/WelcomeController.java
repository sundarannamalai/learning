package learning.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 07/04/18
 * Time: 5:55 PM
 */
@Controller
public class WelcomeController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String showLogin(ModelMap modelMap) {
    modelMap.put("name", getLoggedInUsername());
    return "welcome";
  }

  private String getLoggedInUsername() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal instanceof UserDetails) {
      return ((UserDetails)principal).getUsername();
    }
    return principal.toString();
  }
}
