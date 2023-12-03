package com.prasanna.questions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String difficulty;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer mark;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
