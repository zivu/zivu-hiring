package com.zivu.hiring.resource;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Question;
import com.zivu.hiring.model.Technology;
import com.zivu.hiring.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class that serves REST calls for interview questions
 */
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
    public List<Question> queryInterviewQuestions(@RequestParam(defaultValue = "JUNIOR") Level level,
                                                  @RequestParam(required = false) boolean hasJava,
                                                  @RequestParam(required = false) boolean hasSpring,
                                                  @RequestParam(required = false) boolean hasSql,
                                                  @RequestParam(required = false) boolean hasJavaScript) {
        Question testQuestion = new Question("test question", Level.JUNIOR, Technology.JAVA);
        return List.of(testQuestion);
    }





}
