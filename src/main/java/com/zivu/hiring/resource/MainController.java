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
 * Class that serves REST calls for interview questions
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {

    private final QuestionService service;

    /**
     * Queries DB for the interview questions according to the level of complexity (request parameter)
     * and requested technologies (request parameter).
     *
     * @param level - JUNIOR, MIDDLE or SENIOR level query parameter that will determine
     *              the complexity of the questions.
     * @return interview questions and answers in a JSON form.
     */
    @GetMapping("/questions")
    public List<QuestionData> queryInterviewQuestions(@RequestParam(defaultValue = "JUNIOR") Level level,
                                                      @RequestParam(required = false) boolean hasJava,
                                                      @RequestParam(required = false) boolean hasSpring,
                                                      @RequestParam(required = false) boolean hasSql,
                                                      @RequestParam(required = false) boolean hasJavaScript) {
        log.debug("received the following query params: level = {}, hasJava = {}, " +
                "hasSpring = {}, hasSql = {}, hasJavaScript = {}", level, hasJava, hasSpring, hasSql, hasJavaScript);
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        log.debug("collected the following questions for an interview: {}", questions);
        return questions;
    }

}
