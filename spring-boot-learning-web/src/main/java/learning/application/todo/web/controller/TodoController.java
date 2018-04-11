package learning.application.todo.web.controller;

import learning.application.todo.bean.Todo;
import learning.application.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:58 PM
 */
@Controller
@SessionAttributes("name")
public class TodoController {

  @Autowired
  TodoService todoService;

  @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
  public String listTodos(ModelMap modelMap) {
    modelMap.put("todoList", todoService.getTodoList((String)modelMap.get("name")));
    return "list-todos";
  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
  public String showAddTodo(ModelMap modelMap) {
    return "todo";
  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
  public String addTodo(ModelMap modelMap, @RequestParam(name = "todo-description") String description) {
    todoService.addTodo(description, (String) modelMap.get("name"), new Date(), false);
    return "redirect:/list-todos";
  }
}
