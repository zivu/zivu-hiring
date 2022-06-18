package com.zivu.hiring.service;

import com.zivu.hiring.converter.QuestionToQuestionDataConverter;
import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.model.Technology;
import com.zivu.hiring.persistance.Question;
import com.zivu.hiring.persistance.QuestionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Queries DB for interview questions with specified level of complexity (JUNIOR, MIDDLE, SENIOR)
 * and requested technologies.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionToQuestionDataConverter converter;

    /**
     * Fetches stored interview questions through the repository interface.
     *
     * @param level     - represents complexity of questions: JUNIOR, MIDDLE, SENIOR.
     * @param hasJava   - points whether Java questions should be searched for.
     * @param hasSpring - points whether Spring questions should be searched for.
     * @param hasSql    - points whether Sql questions should be searched for.
     * @param hasJS     - points whether JavaScript questions should be searched for.
     *                  Throws IllegalArgumentException in case no technology was chosen.
     * @return list of interview questions along with answers, level of complexity and technology involved.
     */
    public List<QuestionData> findQuestions(@NonNull Level level, boolean hasJava, boolean hasSpring, boolean hasSql, boolean hasJS) {
        throwExceptionIfNoTechnologyChosen(hasJava, hasSpring, hasSql, hasJS);
        List<Technology> technologies = collectRequestedTechnologies(hasJava, hasSpring, hasSql, hasJS);
        log.info("Retrieving interview questions from DB.");
        List<Question> questions = repository.findByLevelAndTechnologyIn(level, technologies);
        log.info("Successfully retrieved questions from DB.");
        return convertToQuestionData(questions);
    }

    /**
     * Requests all questions (all levels and technologies) form DB.
     * @return all available interview Questions.
     */
    public List<QuestionData> findAllQuestions() {
        log.info("Requesting all questions from DB.");
        List<Question> questions = repository.findAll();
        log.info("Successfully fetched questions from DB.");
        return convertToQuestionData(questions);
    }

    private void throwExceptionIfNoTechnologyChosen(boolean hasJava, boolean hasSpring, boolean hasSql, boolean hasJavaScript) {
        if (!hasJava && !hasSpring && !hasSql && !hasJavaScript) {
            throw new IllegalArgumentException("At least one technology should be specified as \"true\". " +
                    "Add has_java, has_spring, has_sql or has_js URL parameter. " +
                    "For example: http://localhost:8080/questions?level=junior&has_java=true");
        }
    }

    private List<Technology> collectRequestedTechnologies(boolean hasJava, boolean hasSpring, boolean hasSql, boolean hasJavaScript) {
        log.info("Preparing a list of technologies for DB request");
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

    private List<QuestionData> convertToQuestionData(List<Question> questions) {
        log.info("Converting a list of Question to QuestionData type");
        List<QuestionData> questionData = new ArrayList<>(questions.size());
        questions.forEach(question -> questionData.add(converter.convert(question)));
        log.debug("The result of conversion: {}", questionData);
        return questionData;
    }

}
