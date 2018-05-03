package learning.application.todo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 09/04/18
 * Time: 9:27 PM
 */
@Entity
public class Todo {

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 10, message = "Enter at least 10 characters")
	private String description;
	private String user;
	private Date targetDate;
	private boolean completed;

	public Todo() {
		super();
	}

	public Todo(int id, String description, String user, Date targetDate, boolean completed) {
		this.id = id;
		this.description = description;
		this.user = user;
		this.targetDate = targetDate;
		this.completed = completed;
	}

	public Todo(String description, String user, Date targetDate, boolean completed) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Todo todo = (Todo) o;
		return id == todo.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
