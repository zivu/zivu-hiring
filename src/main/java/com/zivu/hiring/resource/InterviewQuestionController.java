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
 * REST controller.
 * Example of query with parameters:
 * http://localhost:8080/questions?level=junior&has_java=true?has_spring=false&has_sql=false&has_js=false
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
     * @param hasJava       - boolean value that includes or excludes Java questions.
     * @param hasSpring     - determines whether Spring Framework questions should be included.
     * @param hasSql        - determines whether Sql questions should be included.
     * @param hasJs         - determines whether JavaScript questions should be included.
     * @return interview questions and answers in a JSON format.
     */
    @GetMapping("/questions")
    public List<QuestionData> queryInterviewQuestions(@RequestParam String level,
                                                      @RequestParam(required = false, name = "has_java") boolean hasJava,
                                                      @RequestParam(required = false, name = "has_spring") boolean hasSpring,
                                                      @RequestParam(required = false, name = "has_sql") boolean hasSql,
                                                      @RequestParam(required = false, name = "has_js") boolean hasJs) {
        log.debug("Received the following query params: level = {}, has_java = {}, " +
                "has_spring = {}, has_sql = {}, has_js = {}", level, hasJava, hasSpring, hasSql, hasJs);
        List<QuestionData> questions = service.findQuestions(Level.valueOf(level.toUpperCase()), hasJava, hasSpring, hasSql, hasJs);
        log.debug("Collected the following questions for the interview: {}", questions);
        return questions;
    }

}
