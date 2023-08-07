package com.arzezan.quiz_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "Quiz")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String difficulty;
    private String category;
}
