package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.QuestionDto;
import com.tishkevich.spring.repositories.QuestionDboRepository;
import com.tishkevich.spring.utils.QuestionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<QuestionDto> getRandomQuestions() {
        Set<Integer> randomNumbersSet = new HashSet<>();
        long currentCount = questionDboRepository.count();
        while (randomNumbersSet.size() < 10) {
            randomNumbersSet.add((int) (Math.random() * currentCount));
        }
        List<QuestionDto> currentList = new ArrayList<>();
        for (Integer num : randomNumbersSet) {
            currentList.add(QuestionConverter.convertQuestion(questionDboRepository.findNecessary(num)));
        }
        return currentList;
    }
}
