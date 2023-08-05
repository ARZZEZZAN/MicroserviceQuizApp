package com.arzezan.quizapp.Service;

import com.arzezan.quizapp.DAO.QuestionDAO;
import com.arzezan.quizapp.DAO.QuizDAO;
import com.arzezan.quizapp.Entity.Question;
import com.arzezan.quizapp.Entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;

    @Autowired
    public QuizService(QuizDAO quizDAO, QuestionDAO questionDAO) {
        this.quizDAO = quizDAO;
        this.questionDAO = questionDAO;
    }

    public ResponseEntity<String> createQuiz(String category, int amount, String title) {
        List<Question> questionList = questionDAO.getRandomQuestionsByCategory(category, amount);
        Quiz quiz = new Quiz();
        quiz.setQuestions(questionList);
        quiz.setTitle(title);
        try {
            quizDAO.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
