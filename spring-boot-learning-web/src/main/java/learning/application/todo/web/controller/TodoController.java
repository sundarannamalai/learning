package learning.application.todo.web.controller;

import learning.application.todo.bean.Todo;
import learning.application.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
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
    modelMap.addAttribute("todo", new Todo(0, "", (String) modelMap.get("user"), new Date(), false));
    return "todo";
  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
  public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()) {
      return "todo";
    }
    todoService.addTodo(todo.getDescription(), (String) modelMap.get("name"), new Date(), false);
    return "redirect:/list-todos";
  }

  @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
  public String deleteTodo(@RequestParam(name = "id") int id) {
    todoService.deleteTodo(id);
    return "redirect:/list-todos";
  }

}
