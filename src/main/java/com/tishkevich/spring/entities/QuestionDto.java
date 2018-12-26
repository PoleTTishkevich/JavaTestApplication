package com.tishkevich.spring.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionDto implements Serializable {
    private int id;
    private String questionText;
    private String[] answerList;
    private String categoryName;


    public QuestionDto(int id, String questionText, String categoryName, String[] answerList) {
        this.id = id;
        this.questionText = questionText;
        this.categoryName = categoryName;
        this.answerList = answerList;
    }

}
