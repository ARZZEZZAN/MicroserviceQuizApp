package com.arzezan.quiz_service.Controller;

import com.arzezan.quiz_service.Entity.QuestionWrapper;
import com.arzezan.quiz_service.Entity.QuizDTO;
import com.arzezan.quiz_service.Entity.Response;
import com.arzezan.quiz_service.Service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(
                quizDTO.getCategory(),
                quizDTO.getAmount(),
                quizDTO.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id) {
        return quizService.getQuizQuestions(id);
    }
    @GetMapping("submit/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Long id,
                                             @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
