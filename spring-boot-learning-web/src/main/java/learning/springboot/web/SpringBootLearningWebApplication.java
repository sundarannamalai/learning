package learning.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"learning.application.todo", "learning.springboot.web"})
public class SpringBootLearningWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningWebApplication.class, args);
	}
}
