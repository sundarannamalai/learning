package learing.jpa;

import learing.jpa.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 01/05/18
 * Time: 2:10 PM
 */
public interface UserCrudRepository extends CrudRepository<User, Long> {
}
