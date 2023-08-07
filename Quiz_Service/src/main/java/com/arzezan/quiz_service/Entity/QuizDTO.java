package com.arzezan.quiz_service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDTO {
    private String category;
    private int amount;
    private String title;
}
