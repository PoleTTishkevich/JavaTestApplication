package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.Category;

import java.util.List;

public interface CategoryService {
    List<String> findAll();
    Category findById(final int id);
    Category findByName(String categoryName);

}
