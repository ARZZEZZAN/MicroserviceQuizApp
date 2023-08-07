package com.arzezan.quiz_service.Service;


import com.arzezan.quiz_service.DAO.QuizDAO;
import com.arzezan.quiz_service.Entity.Question;
import com.arzezan.quiz_service.Entity.QuestionWrapper;
import com.arzezan.quiz_service.Entity.Quiz;
import com.arzezan.quiz_service.Entity.Response;
import com.arzezan.quiz_service.Feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {
    private QuizDAO quizDAO;
    private QuizInterface quizInterface;
    @Autowired
    public QuizService(QuizDAO quizDAO, QuizInterface quizInterface) {
        this.quizDAO = quizDAO;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<String> createQuiz(String category, int amount, String title) {

        try {
            List<Long> questionsId = quizInterface.getQuestionsIdByCategory(category, amount).getBody();
            Quiz  quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsId(questionsId);
            quizDAO.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        try {
            Quiz quiz = quizDAO.findById(id).get();
            List<Long> questionsId = quiz.getQuestionsId();
            List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsFromId(questionsId).getBody();
            return new ResponseEntity<>(questionWrappers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
//        try {
//            int right = 0;
//            Optional<Quiz> quiz = quizDAO.findById(id);
//            List<Question> questions = quiz.get().getQuestions();
//            for(int i = 0; i < responses.size(); i++) {
//                if(responses.get(i).getResponse().equals(questions.get(i).getAnswer())) {
//                    right++;
//                }
//            }
//            return new ResponseEntity<>(right, HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
