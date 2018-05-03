package learning.application.todo.service;

import learning.application.todo.bean.Todo;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private TodoRepository repository;

/*
  static {
    todoList.add(new Todo(1, "Udemy course about Spring boot - Delete you get error", "learn", Date.from(Instant.now()), false));
    todoList.add(new Todo(2, "Learn Telugu", "learn", Date.from(Instant.now().plus(1, ChronoUnit.DAYS)), false));
    todoList.add(new Todo(3, "Read Kindle", "learn", Date.from(Instant.now().plus(2, ChronoUnit.DAYS)), false));
  }*/

	public List<Todo> getTodoList(String user) {
		return repository.findByUser(user);
	}

	public void addTodo(String description, String user, Date date, boolean completed) {
		repository.save(new Todo(description, user, date, completed));
	}

	public void deleteTodo(int id) {
  	repository.deleteById(id);
	}

	public Todo getTodo(int id) {
		return repository.findById(id).orElse(null);
	}

	public void updateTodo(Todo todo) {
		repository.save(todo);
	}
}
