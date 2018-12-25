package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.repositories.QuestionDboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "questionService")
public class QuestionDboServiceImpl implements QuestionDboService {

    private final QuestionDboRepository questionDboRepository;

    @Autowired
    public QuestionDboServiceImpl(QuestionDboRepository questionDboRepository) {
        this.questionDboRepository = questionDboRepository;
    }

    @Override
    public QuestionDbo save(QuestionDbo newQuestion) {
        return questionDboRepository.save(newQuestion);
    }

    @Override
    public List<QuestionDbo> findAll() {
        List<QuestionDbo> list = new ArrayList<>();
        questionDboRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        questionDboRepository.deleteById(id);
    }

    @Override
    public QuestionDbo findById(final int id) {
        Optional<QuestionDbo> optionalQuestionDbo = questionDboRepository.findById(id);
        return optionalQuestionDbo.orElse(null);
    }

    @Override
    public QuestionDbo update(QuestionDbo questionDbo) {
        QuestionDbo user = findById(questionDbo.getId());
        questionDboRepository.save(user);
        return questionDbo;
    }

    @Override
    public List<QuestionDbo> findAllByCategory(Category category){
        return questionDboRepository.findAllByCategory(category);
    }

    @Override
    public long countByCategory(Category category){
        return questionDboRepository.countByCategory(category);
    }

    @Override
    public QuestionDbo findNecessaryFromCategory(Category category, int limit){
        return questionDboRepository.findNecessaryFromCategory(category.getId(), limit);
    }

    @Override
    public QuestionDbo findNecessary(int limit){
        return questionDboRepository.findNecessary(limit);
    }

    @Override
    public long count(){
        return questionDboRepository.count();
    }
}
