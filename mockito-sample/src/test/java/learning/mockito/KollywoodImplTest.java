package learning.mockito;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Year;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 23/04/18
 * Time: 10:48 PM
 */
public class KollywoodImplTest {

  @Test
  public void testTop2Gross() {
    ImdbService imdbMock = Mockito.mock(ImdbService.class);
    Mockito.when(imdbMock.getMovies(2017)).thenReturn(Lists.newArrayList(
        new Movie("Aruvi", "The film portrays the events that occur in the life of Aruvi (Aditi Balan), a rebellious young woman who seeks to expose the consumerist and misogynistic nature of modern", Year.of(2017), 100000.00),
        new Movie("Mersal", "Maaran, a doctor, is invited to Paris for a seminar to honour him for his contribution in the field of medicine. He is arrested by the police when his lookalike murders Dr Zachariah.", Year.of(2017), 5000000.00),
        new Movie("Vikram Vedha", "Vikram, a no-nonsense police officer, accompanied by Simon, his partner, is on the hunt to capture Vedha, a smuggler and a murderer. Vedha tries to change Vikram's life, which leads to a conflict.", Year.of(2017), 2590000.00)
    ));
    KollywoodImpl kollywoodImpl = new KollywoodImpl(imdbMock);
    Assert.assertEquals(7590000, kollywoodImpl.topBoxOfficeCollection(2, 2017), 100000);
  }

  @Test
  public void testTop2GrossWhenOnly1MovieReleased() {
    ImdbService imdbMock = Mockito.mock(ImdbService.class);
    Mockito.when(imdbMock.getMovies(2017)).thenReturn(Lists.newArrayList(
        new Movie("Aruvi", "The film portrays the events that occur in the life of Aruvi (Aditi Balan), a rebellious young woman who seeks to expose the consumerist and misogynistic nature of modern", Year.of(2017), 100000.00)
    ));
    KollywoodImpl kollywoodImpl = new KollywoodImpl(imdbMock);
    Assert.assertEquals(100000.00, kollywoodImpl.topBoxOfficeCollection(2, 2017), 100);
  }
}
