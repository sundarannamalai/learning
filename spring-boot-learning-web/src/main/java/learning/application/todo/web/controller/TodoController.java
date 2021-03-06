package learning.application.todo.web.controller;

import learning.application.todo.bean.Todo;
import learning.application.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:58 PM
 */
@Controller
public class TodoController {

  private final TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
  }

  @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
  public String listTodos(ModelMap modelMap) {
    modelMap.put("todoList", todoService.getTodoList(getLoggedInUsername()));
    return "list-todos";
  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
  public String showAddTodo(ModelMap modelMap) {
    modelMap.addAttribute("todo", new Todo(0, "", getLoggedInUsername(), new Date(), false));
    //return the view
    return "todo";
  }

  private String getLoggedInUsername() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal instanceof UserDetails) {
      return ((UserDetails)principal).getUsername();
    }
    return principal.toString();
  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
  public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()) {
      return "todo";
    }
    todoService.addTodo(todo.getDescription(), getLoggedInUsername(), new Date(), false);
    //redirect to url
    return "redirect:/list-todos";
  }

  @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
  public String showUpdateTodo(@RequestParam(name = "id") int id, ModelMap modelMap) {
    Todo todo = todoService.getTodo(id);
    modelMap.put("todo", todo);
    return "todo";
  }

  @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
  public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()) {
      return "todo";
    }
    todo.setUser(getLoggedInUsername());
    todoService.updateTodo(todo);
    return "redirect:/list-todos";
  }

  @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
  public String deleteTodo(@RequestParam(name = "id") int id) {
    if(id == 1) {
      throw new RuntimeException("something failed");
    }
    todoService.deleteTodo(id);
    return "redirect:/list-todos";
  }

}
