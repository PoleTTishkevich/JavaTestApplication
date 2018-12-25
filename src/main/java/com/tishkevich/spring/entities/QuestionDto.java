package com.tishkevich.spring.entities;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private int id;
    private String questionText;
    private List<String> answerList;
    private String categoryName;


    public QuestionDto(int id, String questionText, String categoryName, List<String> answerList) {
        this.id = id;
        this.questionText = questionText;
        this.categoryName = categoryName;
        this.answerList = answerList;
    }

}
