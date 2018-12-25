package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<Category> tmpList = new ArrayList<>();
        categoryRepository.findAll().iterator().forEachRemaining(tmpList::add);
        return tmpList;
    }

    @Override
    public Category findById(final int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public Category findByName(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);
        return optionalCategory.orElse(null);
    }
}
