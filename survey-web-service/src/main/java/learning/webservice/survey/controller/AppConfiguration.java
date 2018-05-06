package learning.webservice.survey.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 01/05/18
 * Time: 11:51 AM
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {
	private String name;
	private String description;
	private String author;
	private int version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
