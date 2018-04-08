package learning.web.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 07/04/18
 * Time: 5:55 PM
 */
@Controller
public class LoginController {

  @RequestMapping("/login")
  public String login(@RequestParam String name, ModelMap modelMap) {
    modelMap.put("name", name);
    return "login";
  }
}
