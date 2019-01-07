package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.AnswerDto;
import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.QuestionDto;

import java.util.List;
import java.util.Set;

public interface QuestionDboService {
    QuestionDbo save(QuestionDbo newQuestion);

    List<QuestionDto> findAll();

    List<QuestionDto> getRandomQuestions();

    void delete(int id);

    QuestionDbo findById(final int id);

    QuestionDbo update(QuestionDbo userDto);

    List<QuestionDbo> findAllByCategory(Category category);

    long countByCategory(Category category);

    long count();

    List<QuestionDbo> findNecessary(int limit);

    List<QuestionDto> findNecessaryFromCategory(Category category, int limit);

    Integer checkAnswers(List<AnswerDto> answers);

    List<QuestionDbo> findAllWithLimit(Long startPosition, int count);

    List<QuestionDbo> findAllByCategoryWithLimit(int categoryId, Long startPosition, int count);

}
