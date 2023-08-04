package com.arzezan.quizapp.Service;

import com.arzezan.quizapp.DAO.QuestionDAO;
import com.arzezan.quizapp.Entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }
}
