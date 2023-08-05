package com.arzezan.quizapp.DAO;

import com.arzezan.quizapp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizDAO extends JpaRepository<Quiz, Long> {

}
