package learing.jpa;

import learing.jpa.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 01/05/18
 * Time: 2:10 PM
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserCrudRepository extends PagingAndSortingRepository<User, Long> {
	@RestResource(path = "/byRole")
	List<User> findByRole(@Param("role") String role);
}
