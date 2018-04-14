package learning.application.todo.service;

import learning.application.todo.bean.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:28 PM
 */
@Service
public class TodoService {

  private static List<Todo> todoList = new ArrayList<>();
  private static int count = 3;

  static {
    todoList.add(new Todo(1, "Udemy course about Spring boot", "Sundar", Date.from(Instant.now()), false));
    todoList.add(new Todo(2, "Learn Telugu", "Sundar", Date.from(Instant.now().plus(1, ChronoUnit.DAYS)), false));
    todoList.add(new Todo(3, "Read Kindle", "Sundar", Date.from(Instant.now().plus(2, ChronoUnit.DAYS)), false));
  }

  public List<Todo> getTodoList(String user) {
    return todoList.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());
  }

  public void addTodo(String description, String user, Date date, boolean completed) {
    todoList.add(new Todo(++count, description, user, date, completed));
  }

  public void deleteTodo(int id) {
    todoList.removeIf((Todo todo) -> todo.getId() == id);
  }

  public Todo getTodo(int id) {
    return todoList.stream().
        filter((Todo todo) -> todo.getId() == id).
        findFirst().
        orElse(null);
  }

  public void updateTodo(Todo todo) {
    todoList.remove(todo);
    todoList.add(todo);
  }
}
