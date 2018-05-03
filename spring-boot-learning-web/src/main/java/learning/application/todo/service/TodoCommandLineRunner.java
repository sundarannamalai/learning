package learning.application.todo.service;

import learning.application.todo.bean.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 03/05/18
 * Time: 11:58 PM
 */
@Component
public class TodoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private TodoRepository repository;

	@Override
	public void run(String... args) throws Exception {
		List<Todo> todoList = new ArrayList<>();
		todoList.add(new Todo("Udemy course about Spring boot - Delete you get error", "learn", Date.from(Instant.now()), false));
		todoList.add(new Todo("Learn Telugu", "learn", Date.from(Instant.now().plus(1, ChronoUnit.DAYS)), false));
		todoList.add(new Todo("Read Kindle", "learn", Date.from(Instant.now().plus(2, ChronoUnit.DAYS)), false));
		repository.saveAll(todoList);
	}
}
