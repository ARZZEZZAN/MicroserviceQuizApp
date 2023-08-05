package com.arzezan.quizapp.Service;

import com.arzezan.quizapp.DAO.QuestionDAO;
import com.arzezan.quizapp.DAO.QuizDAO;
import com.arzezan.quizapp.Entity.Question;
import com.arzezan.quizapp.Entity.QuestionWrapper;
import com.arzezan.quizapp.Entity.Quiz;
import com.arzezan.quizapp.Entity.Response;
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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        try {
            Optional<Quiz> quiz = quizDAO.findById(id);
            List<Question> questionList = quiz.get().getQuestions();
            List<QuestionWrapper> questionWrapperList = new ArrayList<>();
            for(Question question : questionList) {
                QuestionWrapper questionWrapper = new QuestionWrapper(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4());
                questionWrapperList.add(questionWrapper);
            }
            return new ResponseEntity<>(questionWrapperList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        try {
            int right = 0;
            Optional<Quiz> quiz = quizDAO.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            for(int i = 0; i < responses.size(); i++) {
                if(responses.get(i).getResponse().equals(questions.get(i).getAnswer())) {
                    right++;
                }
            }
            return new ResponseEntity<>(right, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
