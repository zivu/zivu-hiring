package com.zivu.hiring.persistance;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the custom interview repository
 * @see com.zivu.hiring.persistance.QuestionRepositoryCustom
 */
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findFiveNewQuestionsForLevelAndEveryTechnology(Level level, Set<Technology> technologies) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = criteriaBuilder.createQuery(Question.class);
        Root<Question> question = query.from(Question.class);
        Path<Object> levelPath = question.get("level");
        Path<Object> technologyPath = question.get("technology");
        Set<Predicate> predicates = new HashSet<>(technologies.size());
        technologies.forEach(technology -> predicates.add(criteriaBuilder.equal(technologyPath, technology)));
        query.select(question)
                .where(criteriaBuilder.equal(levelPath, level))
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query)
                .getResultList();
    }

}
