package com.tishkevich.spring.utils;

import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.QuestionDto;

import java.util.Arrays;

public class QuestionConverter {

    public static QuestionDto convertQuestion(QuestionDbo questionDbo){
        return new QuestionDto(questionDbo.getId(), questionDbo.getQuestionText(), questionDbo.getCategory().getName(), Arrays.asList(questionDbo.getFirstAnswer(), questionDbo.getSecondAnswer(), questionDbo.getThirdAnswer(), questionDbo.getForthAnswer()));
    }
}
