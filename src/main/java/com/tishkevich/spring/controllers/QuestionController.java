package com.tishkevich.spring.controllers;

import com.tishkevich.spring.entities.*;
import com.tishkevich.spring.service.CategoryService;
import com.tishkevich.spring.service.QuestionDboService;
import com.tishkevich.spring.utils.QuestionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class QuestionController {
    private final QuestionDboService questionDboService;

    private final CategoryService categoryService;

    @Autowired
    public QuestionController(QuestionDboService questionDboService, CategoryService categoryService) {
        this.questionDboService = questionDboService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/getAllQuestions")
    public ApiResponse getAllQuestions() {
        List<QuestionDto> currentList = new ArrayList<>();
        for (QuestionDbo question : questionDboService.findAll()) {
            currentList.add(QuestionConverter.convertQuestion(question));
        }
        return new ApiResponse(HttpStatus.OK.value(), "Questions got successfully.", currentList);

    }

    @GetMapping(value = "/getAllCategories")
    public ApiResponse getAllCategories() {
        List<String> currentList = new ArrayList<>();
        for (Category category : categoryService.findAll()) {
            currentList.add(category.getName());
        }
        return new ApiResponse(HttpStatus.OK.value(), "Questions got successfully.", currentList);
    }

    @GetMapping(value = "/getRandomQuestions")
    public ApiResponse getRandomQuestions() {
        Set<Integer> randomNumbersSet = new HashSet<>();
        long currentCount = questionDboService.count();
        while (randomNumbersSet.size() < 10) {
            randomNumbersSet.add((int) (Math.random() * currentCount));
        }
        List<QuestionDto> currentList = new ArrayList<>();
        for (Integer num : randomNumbersSet) {
            currentList.add(QuestionConverter.convertQuestion(questionDboService.findNecessary(num)));
        }
        return new ApiResponse(HttpStatus.OK.value(), "Questions got successfully.", currentList);
    }

    @GetMapping(value = "/getRandomQuestions/{categoryName}")
    public ApiResponse getRandomQuestionsByCategory(@PathVariable String categoryName) {
        Set<Integer> randomNumbersSet = new HashSet<>();
        Category currentCategory = categoryService.findByName(categoryName);
        if (currentCategory != null) {
            long currentCount = questionDboService.countByCategory(currentCategory);
            while (randomNumbersSet.size() < 10) {
                randomNumbersSet.add((int) (Math.random() * currentCount));
            }
            List<QuestionDto> currentList = new ArrayList<>();
            for (Integer num : randomNumbersSet) {
                currentList.add(QuestionConverter.convertQuestion(questionDboService.findNecessaryFromCategory(currentCategory, num)));
            }
            return new ApiResponse(HttpStatus.OK.value(), "Questions got successfully.", currentList);
        } else {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Category hasn't been found.", "");
        }
    }

    @PostMapping(value = "/add")
    public ApiResponse addQuestion(@RequestBody QuestionDbo newQuestion) {
        questionDboService.save(newQuestion);
        return new ApiResponse(HttpStatus.OK.value(), "Question added successfully.", newQuestion);
    }

    @PostMapping(value = "/check")
    public ApiResponse checkAnswers(@RequestBody List<AnswerDto> answers) {
        int result = 0;
        int count = 0;
        for (AnswerDto answer : answers) {
            count++;
            QuestionDbo tmpQuestion = questionDboService.findById(answer.getId());
            if (answer.getAnswerNumber() == tmpQuestion.getCorrectAnswerNumber()) {
                result++;
            }
        }
        return new ApiResponse(HttpStatus.OK.value(), "Question added successfully.", count == 0 ? 0 : result / count);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable int id) {
        questionDboService.delete(id);
        return new ApiResponse(HttpStatus.OK.value(), "Question deleted successfully.", null);
    }


}
