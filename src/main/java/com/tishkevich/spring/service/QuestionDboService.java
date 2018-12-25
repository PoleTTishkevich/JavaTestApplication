package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;

import java.util.List;

public interface QuestionDboService {
    QuestionDbo save(QuestionDbo newQuestion);

    List<QuestionDbo> findAll();

    void delete(int id);

    QuestionDbo findById(final int id);

    QuestionDbo update(QuestionDbo userDto);

    List<QuestionDbo> findAllByCategory(Category category);

    long countByCategory(Category category);

    long count();

    QuestionDbo findNecessary(int limit);

    QuestionDbo findNecessaryFromCategory(Category category, int limit);
}
