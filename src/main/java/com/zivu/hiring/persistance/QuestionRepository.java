package com.zivu.hiring.persistance;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Repository interface used to query DB for interview questions.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.level = :level AND q.technology IN :technologies")
    List<Question> findByLevelAndTechnologyIn(@Param("level") Level level, @Param("technologies") Collection<Technology> technologies);
    
}
