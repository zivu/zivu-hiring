package com.zivu.hiring.persistance;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;

import java.util.List;
import java.util.Set;

/**
 * Custom interface for fetching interview questions.
 */
public interface QuestionRepositoryCustom {

    /**
     * Finds 5 newest interview questions for every technology specified (i.e. 5 for java, 5 for spring...)
     * considering the chosen level of complexity (i.e. jun, mid, senior).
     * @param level of complexity. All questions will have the same level.
     * @param technologies that interviewer wants to check candidate knowledge on.
     * @return interview questions for specified level of complexity and technology.
     */
    List<Question> findFiveNewQuestionsForLevelAndEveryTechnology(Level level, Set<Technology> technologies);

}
