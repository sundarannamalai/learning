package learning.application.todo.web.controller;

import learning.application.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:58 PM
 */
@Controller
public class TodoController {

  @Autowired
  TodoService todoService;

  @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
  public String listTodos(ModelMap modelMap) {
    modelMap.put("todoList", todoService.getTodoList("Sundar"));
    return "list-todos";
  }
}
