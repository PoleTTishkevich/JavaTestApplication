package com.tishkevich.spring.frontend;

import java.time.LocalTime;
import java.util.List;

import com.tishkevich.spring.entities.QuestionDto;
import com.tishkevich.spring.service.QuestionDboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBean {

    @Autowired
    QuestionDboService questionDboService;

    public String getMessage() {
        return "Button was clicked at " + LocalTime.now();
    }

    public List<QuestionDto> getRandomQuestions(){
        return questionDboService.getRandomQuestions();
    }
}
