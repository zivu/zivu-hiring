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
 * Example of query with parameters:
 * http://localhost:8080/questions?level=JUNIOR&hasava=true?hasspring=false&hassql=false&hasjavascript=false
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewQuestionController {

    private final QuestionService service;

    /**
     * Fetches specified number of interview questions that fit to a requested level of complexity and technologies.
     * level, number and at least one technology should be chosen, otherwise IllegalArgumentException will be thrown.
     *
     * @param level         - JUNIOR, MIDDLE or SENIOR level query parameter that will determine
     *                      the complexity of the questions.
     * @param number        - how many questions should be generated for every technology. If a provided number will
     *                      exceed a number of questions stored, then only available questions will be returned.
     * @param hasJava       - boolean value that includes or excludes Java questions.
     * @param hasSpring     - determines whether Spring Framework questions should be included.
     * @param hasSql        - determines whether Sql questions should be included.
     * @param hasJavaScript - determines whether JavaScript questions should be included.
     * @return interview questions and answers in a JSON format.
     */
    @GetMapping("/questions")
    public List<QuestionData> queryInterviewQuestions(@RequestParam Level level,
                                                      @RequestParam int number,
                                                      @RequestParam(required = false) boolean hasJava,
                                                      @RequestParam(required = false) boolean hasSpring,
                                                      @RequestParam(required = false) boolean hasSql,
                                                      @RequestParam(required = false) boolean hasJavaScript) {
        log.debug("Received the following query params: level = {}, number = {}, hasJava = {}, " +
                "hasSpring = {}, hasSql = {}, hasJavaScript = {}", level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        log.debug("Collected the following questions for the interview: {}", questions);
        return questions;
    }

}
