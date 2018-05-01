package learing.jpa.model;

import learing.jpa.UserCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 01/05/18
 * Time: 2:08 PM
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private UserCrudRepository repository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	public UserCommandLineRunner(UserCrudRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("UserCommandLineRunner");
		repository.save(new User("learn1", "Admin"));
		repository.save(new User("learn2", "User"));

		repository.findAll().forEach(user -> LOGGER.info(user.toString()));

		LOGGER.info("Admin users list....");
		repository.findByRole("Admin").forEach(user -> LOGGER.info(user.toString()));
	}
}
