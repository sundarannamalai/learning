package learning.web.service.survey.model;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 28/04/18
 * Time: 8:32 PM
 */
public class Survey {
  private String id;
  private String title;
  private String description;
  private List<Question> questions;

  public Survey(String id, String title, String description,
                List<Question> questions) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.questions = questions;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  @Override
  public String toString() {
    return "Survey [id=" + id + ", title=" + title + ", description="
        + description + ", questions=" + questions + "]";
  }

}
