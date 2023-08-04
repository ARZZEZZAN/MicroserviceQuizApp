package com.arzezan.quizapp.Controller;

import com.arzezan.quizapp.Entity.Question;
import com.arzezan.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
