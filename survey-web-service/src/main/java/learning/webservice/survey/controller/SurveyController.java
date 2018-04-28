package learning.webservice.survey.controller;

import learning.webservice.survey.model.Question;
import learning.webservice.survey.model.Survey;
import learning.webservice.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 28/04/18
 * Time: 8:35 PM
 */
@RestController
public class SurveyController {

  private SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping("/surveys")
  public List<Survey> retrieveAllSurveys() {
    return surveyService.retrieveAllSurveys();
  }

  @GetMapping("/surveys/{surveyId}/questions")
  public List<Question> retrieveQuestions(@PathVariable String surveyId) {
    return surveyService.retrieveQuestions(surveyId);
  }

  @PostMapping("/surveys/{surveyId}/questions")
  public ResponseEntity<?> addQuestion(@PathVariable String surveyId, @RequestBody Question question) {
    Question created = surveyService.addQuestion(surveyId, question);
    if(created == null) {
      return ResponseEntity.noContent().build();
    }
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(created.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/surveys/{surveyId}/questions/{questionId}")
  public Question retrieveQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    return surveyService.retrieveQuestion(surveyId, questionId);
  }
}
