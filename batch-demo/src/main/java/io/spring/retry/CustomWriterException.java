package io.spring.retry;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 17/06/18
 * Time: 4:22 PM
 */
public class CustomWriterException extends RuntimeException {

  public CustomWriterException(String message) {
    super(message);
  }
}
