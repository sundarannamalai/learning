package learning.springboot.web.service;

import org.springframework.stereotype.Service;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 08/04/18
 * Time: 10:12 PM
 */
@Service
public class LoginService {

  public boolean validate(String userName, String password) {
    return userName.equals("sundar")
        && password.equals("anna");
  }
}
