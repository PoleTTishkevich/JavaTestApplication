package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.AnswerDto;
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
    public List<QuestionDto> findAll() {
        List<QuestionDbo> list = new ArrayList<>();
        questionDboRepository.findAll().iterator().forEachRemaining(list::add);
        List<QuestionDto> newList = new ArrayList<>();
        for (QuestionDbo question : list) {
            newList.add(QuestionConverter.convertQuestionToDto(question));
        }
        return newList;
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
    public List<QuestionDbo> findAllByCategory(Category category) {
        return questionDboRepository.findAllByCategory(category);
    }

    @Override
    public long countByCategory(Category category) {
        return questionDboRepository.countByCategory(category);
    }

    @Override
    public List<QuestionDto> findNecessaryFromCategory(Category category, int limit) {
        int count = (int) questionDboRepository.countByCategory(category);
        long startNumber = new Random().nextInt((count - limit));
        List<QuestionDbo> list = questionDboRepository.findAllByCategory(category.getId(), startNumber);
        List<QuestionDto> newList = new ArrayList<>();
        for (QuestionDbo question : list) {
            newList.add(QuestionConverter.convertQuestionToDto(question));
        }
        return newList;
    }

    @Override
    public List<QuestionDbo> findNecessary(int limit) {
        Set<Integer> arr = new HashSet<>();
        int max = (int) count();
        while (arr.size() < limit) {
            arr.add(new Random().nextInt((max - 1) + 1) + 1);
        }
        Integer[] mas = arr.toArray(new Integer[arr.size()]);
        return questionDboRepository.findNecessary(mas);
    }

    @Override
    public long count() {
        return questionDboRepository.count();
    }

    @Override
    public List<QuestionDto> getRandomQuestions() {
        List<QuestionDbo> list = findNecessary(10);
        List<QuestionDto> newList = new ArrayList<>();
        for (QuestionDbo question : list) {
            newList.add(QuestionConverter.convertQuestionToDto(question));
        }
        return newList;
    }

    @Override
    public Integer checkAnswers(final List<AnswerDto> answers) {
        int result = 0;
        int count = 0;
        for (AnswerDto answer : answers) {
            count++;
            QuestionDbo tmpQuestion = questionDboRepository.findById(answer.getId()).orElse(null);
            if (answer.getAnswerNumber() == tmpQuestion.getCorrectAnswerNumber()) {
                result++;
            }
        }
        return count == 0 ? 0 : result / count;
    }

    @Override
    public List<QuestionDbo> findAllWithLimit(Long startPosition, int count) {
        return questionDboRepository.findAllWithLimit(startPosition, count);
    }

    @Override
    public List<QuestionDbo> findAllByCategoryWithLimit(int categoryId, Long startPosition, int count) {
        return questionDboRepository.findAllByCategoryWithLimit(categoryId, startPosition, count);
    }


}
