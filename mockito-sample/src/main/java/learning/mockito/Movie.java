package learning.mockito;

import java.time.Year;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 23/04/18
 * Time: 10:19 PM
 */
public class Movie {
  private String name;
  private String storyLine;
  private Year yearOfRelease;
  private Double boxOfficeCollection;

  public Movie() {
  }

  public Movie(String name, String storyLine, Year yearOfRelease, Double boxOfficeCollection) {
    this.name = name;
    this.storyLine = storyLine;
    this.yearOfRelease = yearOfRelease;
    this.boxOfficeCollection = boxOfficeCollection;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStoryLine() {
    return storyLine;
  }

  public void setStoryLine(String storyLine) {
    this.storyLine = storyLine;
  }

  public Year getYearOfRelease() {
    return yearOfRelease;
  }

  public void setYearOfRelease(Year yearOfRelease) {
    this.yearOfRelease = yearOfRelease;
  }

  public Double getBoxOfficeCollection() {
    return boxOfficeCollection;
  }

  public void setBoxOfficeCollection(Double boxOfficeCollection) {
    this.boxOfficeCollection = boxOfficeCollection;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "name='" + name + '\'' +
        ", storyLine='" + storyLine + '\'' +
        ", yearOfRelease=" + yearOfRelease +
        ", boxOfficeCollection=" + boxOfficeCollection +
        '}';
  }
}
