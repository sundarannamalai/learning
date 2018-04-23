package learning.mockito;

import java.util.Comparator;
import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 23/04/18
 * Time: 10:17 PM
 */
public class KollywoodImpl {

  private ImdbService imdbService;

  public KollywoodImpl(ImdbService imdbService) {
    this.imdbService = imdbService;
  }

  public double topBoxOfficeCollection(int topN, int year) {
    List<Movie> allMovies = imdbService.getMovies(year);
    allMovies.sort(Comparator.comparing(Movie::getBoxOfficeCollection).reversed());
    return allMovies.stream().mapToDouble(Movie::getBoxOfficeCollection).limit(topN).sum();
  }
}
