package com.arzezan.quiz_service.DAO;


import com.arzezan.quiz_service.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizDAO extends JpaRepository<Quiz, Long> {

}
