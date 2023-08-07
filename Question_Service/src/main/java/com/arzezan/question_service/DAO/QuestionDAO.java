package com.arzezan.question_service.DAO;

import com.arzezan.question_service.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT q.id FROM Quiz.question q where q.category=:category ORDER BY RANDOM() LIMIT :amount",nativeQuery = true)
    List<Long> getQuestionsIdByCategory(String category, int amount);
}
