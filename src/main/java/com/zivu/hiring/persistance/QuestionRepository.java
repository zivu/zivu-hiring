package com.zivu.hiring.persistance;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Repository interface used to query DB for interview questions.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<QuestionData> findByLevelAndTechnologyIn(Level level, Collection<Technology> technologies);
    
}
