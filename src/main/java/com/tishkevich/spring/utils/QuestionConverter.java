package com.tishkevich.spring.utils;

import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.QuestionDto;

public class QuestionConverter {

    public static QuestionDto convertQuestionToDto(QuestionDbo questionDbo){
        String[] tmpList = new String[4];
        tmpList[0]=questionDbo.getFirstAnswer();
        tmpList[1]=questionDbo.getSecondAnswer();
        tmpList[2]=questionDbo.getThirdAnswer();
        tmpList[3]=questionDbo.getForthAnswer();
        return new QuestionDto(questionDbo.getId(), questionDbo.getQuestionText(), tmpList);
    }
}
