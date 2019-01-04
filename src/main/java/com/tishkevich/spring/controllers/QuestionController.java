package com.tishkevich.spring.controllers;

import com.tishkevich.spring.entities.*;
import com.tishkevich.spring.service.CategoryService;
import com.tishkevich.spring.service.QuestionDboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ApiResponse<QuestionDto> getAllQuestions() {
        List<QuestionDto> currentList = questionDboService.findAll();
        return new ApiResponse<QuestionDto>(HttpStatus.OK.value(), "Questions got successfully.", currentList.toArray(new QuestionDto[currentList.size()]));

    }

    @GetMapping(value = "/getAllCategories")
    public ApiResponse<Category> getAllCategories() {
        List<String> currentList = categoryService.findAll();
        return new ApiResponse<Category>(HttpStatus.OK.value(), "Questions got successfully.", currentList.toArray(new Category[currentList.size()]));
    }

    @GetMapping(value = "/getRandomQuestions")
    public ApiResponse<QuestionDto> getRandomQuestions() {
        List<QuestionDto> currentList = questionDboService.getRandomQuestions();
        return new ApiResponse<QuestionDto>(HttpStatus.OK.value(), "Random questions got successfully.", currentList.toArray(new QuestionDto[currentList.size()]));
    }

    @GetMapping(value = "/getRandomQuestions/{categoryName}")
    public ApiResponse<QuestionDto> getRandomQuestionsByCategory(@PathVariable String categoryName) {
        Category currentCategory = categoryService.findByName(categoryName);
        List<QuestionDto> currentList = questionDboService.findNecessaryFromCategory(currentCategory, 10);
        if (currentCategory != null) {
            return new ApiResponse<QuestionDto>(HttpStatus.OK.value(), "Questions got successfully.", currentList.toArray(new QuestionDto[currentList.size()]));
        } else {
            return new ApiResponse<QuestionDto>(HttpStatus.BAD_REQUEST.value(), "Category hasn't been found.", null);
        }
    }

    @PostMapping(value = "/add")
    public ApiResponse<QuestionDbo> addQuestion(@RequestBody QuestionDbo newQuestion) {
        questionDboService.save(newQuestion);
        return new ApiResponse<QuestionDbo>(HttpStatus.OK.value(), "Question added successfully.", new QuestionDbo[]{newQuestion});
    }

    @PostMapping(value = "/check")
    public ApiResponse<Integer> checkAnswers(@RequestBody List<AnswerDto> answers) {
        Integer result = questionDboService.checkAnswers(answers);
        return new ApiResponse<Integer>(HttpStatus.OK.value(), "Question added successfully.", new Integer[]{result});
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable int id) {
        questionDboService.delete(id);
        return new ApiResponse(HttpStatus.OK.value(), "Question deleted successfully.", null);
    }
}
