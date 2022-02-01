package com.zivu.hiring.resource;

import com.zivu.hiring.model.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class that serves REST calls for interview questions
 */
@RestController
public class MainController {

    /**
     * Queries DB for the interview questions according to the level of complexity (request parameter)
     * and requested technologies (request parameter).
     *
     * @param level - JUNIOR, MIDDLE or SENIOR level query parameter that will determine
     *              the complexity of the questions.
     * @return interview questions and answers in a JSON form.
     */
    @GetMapping("/")
    public String queryInterviewQuestions(@RequestParam(defaultValue = "JUNIOR") Level level,
                                          @RequestParam(required = false) boolean hasJava,
                                          @RequestParam(required = false) boolean hasSpring,
                                          @RequestParam(required = false) boolean hasSql) {
        return "test";
    }





}
