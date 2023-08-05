package com.arzezan.quizapp.Controller;

import com.arzezan.quizapp.Entity.QuestionWrapper;
import com.arzezan.quizapp.Entity.Response;
import com.arzezan.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int amount,
                                             @RequestParam String title) {
        return quizService.createQuiz(category, amount, title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id) {
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> createQuiz(@PathVariable Long id,
                                             @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
