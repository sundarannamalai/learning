package learning.mockito;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 23/04/18
 * Time: 10:19 PM
 */
public interface ImdbService {
  List<Movie> getMovies(int year);
}
