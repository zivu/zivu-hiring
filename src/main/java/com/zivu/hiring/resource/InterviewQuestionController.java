package com.zivu.hiring.resource;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is responsible for handling incoming REST calls for interview questions.
 * Example URL could be as short as:
 * http://localhost:8080/questions,
 * which will return only Java interview questions for a Junior Java Developer.
 * Additionally, URL could have parameters that will narrow search down to specific technologies:
 * level - level of complexity; could be JUNIOR, MIDDLE, SENIOR;
 * hasJava - specifies whether Java questions should be included;
 * hasSpring - should Spring Framework questions be included?
 * hasSql - should SQL questions be included?
 * hasJavaScript - should JavaScript questions be included?
 * Example of query with parameters:
 * http://localhost:8080/questions?level=JUNIOR&hasava=true?hasspring=false&hassql=false&hasjavascript=false
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewQuestionController {

    private final QuestionService service;

    /**
     * Queries service for interview questions according to a requested level of complexity and technologies.
     *
     * @param level         - JUNIOR, MIDDLE or SENIOR level query parameter that will determine
     *                      the complexity of the questions. Default value is JUNIOR.
     * @param hasJava       - boolean value that includes or excludes Java questions. Default value is true;
     * @param hasSpring     - determines whether Spring Framework questions should be included.
     * @param hasSql        - determines whether Sql questions should be included.
     * @param hasJavaScript - determines whether JavaScript questions should be included.
     * @return interview questions and answers in a JSON form.
     */
    @GetMapping("/questions")
    public List<QuestionData> queryInterviewQuestions(@RequestParam(required = false) Level level,
                                                      @RequestParam(required = false) boolean hasJava,
                                                      @RequestParam(required = false) boolean hasSpring,
                                                      @RequestParam(required = false) boolean hasSql,
                                                      @RequestParam(required = false) boolean hasJavaScript) {
        log.debug("received the following query params: level = {}, hasJava = {}, " +
                "hasSpring = {}, hasSql = {}, hasJavaScript = {}", level, hasJava, hasSpring, hasSql, hasJavaScript);
        List<QuestionData> questions;
        if (null == level) {
            questions = service.findAllQuestions();
        } else {
            questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        }
        log.debug("collected the following questions for the interview: {}", questions);
        return questions;
    }

}
