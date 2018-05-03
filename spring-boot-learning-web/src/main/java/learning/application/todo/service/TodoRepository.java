package learning.application.todo.service;

import learning.application.todo.bean.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 03/05/18
 * Time: 11:55 PM
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(String user);
}
