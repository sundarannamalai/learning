package learning.application.todo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 14/04/18
 * Time: 11:54 PM
 */
@Controller("error")
public class ErrorController {

  @ExceptionHandler
  public ModelAndView handleRE(RuntimeException runTex, HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", runTex.getLocalizedMessage());
    modelAndView.addObject("url", request.getRequestURL());
    modelAndView.setViewName("error");
    return modelAndView;
  }
}
