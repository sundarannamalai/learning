package learning.application.todo.bean;

import java.util.Date;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:27 PM
 */
public class Todo {
  private int id;
  private String description;
  private String user;
  private Date targetDate;
  private boolean completed;

  public Todo() {
  }

  public Todo(int id, String description, String user, Date targetDate, boolean completed) {
    this.id = id;
    this.description = description;
    this.user = user;
    this.targetDate = targetDate;
    this.completed = completed;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Date getTargetDate() {
    return targetDate;
  }

  public void setTargetDate(Date targetDate) {
    this.targetDate = targetDate;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return "Todo{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", user='" + user + '\'' +
        ", targetDate=" + targetDate +
        ", completed=" + completed +
        '}';
  }
}
