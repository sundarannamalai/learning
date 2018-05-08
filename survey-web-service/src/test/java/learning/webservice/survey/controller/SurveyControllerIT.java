package learning.webservice.survey.controller;

import learning.webservice.Application;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 01/05/18
 * Time: 11:20 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void test_Retrieve_A_Survey_Question() throws JSONException {
    HttpHeaders headers = new HttpHeaders();
    String base64Creds = Base64.getEncoder().encodeToString("surveyuser:suser123".getBytes());
    headers.set("Authorization", "Basic " + base64Creds);
    headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
    HttpEntity entity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/surveys/Survey1/questions/Question1", HttpMethod.GET, entity, String.class);
    JSONAssert.assertEquals("{\"correctAnswer\" : \"Russia\"}", responseEntity.getBody(), false);
  }
}
