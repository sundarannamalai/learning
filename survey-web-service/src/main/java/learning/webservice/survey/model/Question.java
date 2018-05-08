package learning.webservice.survey.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 28/04/18
 * Time: 8:32 PM
 */
public class Question extends ResourceSupport {
  private String qId;
  private String description;
  private String correctAnswer;
  private List<String> options;

  // Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
  // Can not construct instance of com.in28minutes.springboot.model.Question:
  // no suitable constructor found, can not deserialize from Object value
  // (missing default constructor or creator, or perhaps need to add/enable
  // type information?)
  public Question() {

  }

  public Question(String qId, String description, String correctAnswer,
                  List<String> options) {
    super();
    this.qId = qId;
    this.description = description;
    this.correctAnswer = correctAnswer;
    this.options = options;
  }

  public String getQId() {
    return qId;
  }

  public void setQId(String qId) {
    this.qId = qId;
  }

  public String getDescription() {
    return description;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public List<String> getOptions() {
    return options;
  }

  @Override
  public String toString() {
    return String
        .format("Question [qId=%s, description=%s, correctAnswer=%s, options=%s]",
            qId, description, correctAnswer, options);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((qId == null) ? 0 : qId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Question other = (Question) obj;
    if (qId == null) {
      if (other.qId != null)
        return false;
    } else if (!qId.equals(other.qId))
      return false;
    return true;
  }

}