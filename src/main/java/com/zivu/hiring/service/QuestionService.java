package com.zivu.hiring.service;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.model.Technology;
import com.zivu.hiring.persistance.QuestionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will process received from DB questions and returns to Controller.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService {

    private final QuestionRepository repository;

    /**
     * Method that fetches stored interview questions through the repository.
     *
     * @param level         - represents complexity of questions: JUNIOR, MIDDLE, SENIOR.
     * @param hasJava       - points whether Java questions should be searched for.
     * @param hasSpring     - points whether Spring questions should be searched for.
     * @param hasSql        - points whether Sql questions should be searched for.
     * @param hasJavaScript - points whether JavaScript questions should be searched for.
     * @return list of interview questions along with answers, level of complexity and technology involved.
     */
    public List<QuestionData> findQuestions(@NonNull Level level, boolean hasJava, boolean hasSpring, boolean hasSql, boolean hasJavaScript) {
        log.info("retrieving interview questions from DB");
        List<Technology> technologies = createTechnologies(hasJava, hasSpring, hasSql, hasJavaScript);
        List<QuestionData> questions = repository.findByLevelAndTechnologyIn(level, technologies);
        log.info("successfully retrieved questions from DB");
        return questions;
    }

    private List<Technology> createTechnologies(boolean hasJava, boolean hasSpring, boolean hasSql, boolean hasJavaScript) {
        List<Technology> technologies = new ArrayList<>(4);
        if (hasJava) {
            technologies.add(Technology.JAVA);
        }
        if (hasSpring) {
            technologies.add(Technology.SPRING);
        }
        if (hasSql) {
            technologies.add(Technology.SQL);
        }
        if (hasJavaScript) {
            technologies.add(Technology.JAVA_SCRIPT);
        }
        log.debug("The following technology list will be queried against DB: {}", technologies);
        return technologies;
    }

}
