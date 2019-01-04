package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.ResultEntity;
import com.tishkevich.spring.repositories.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultEntity saveResult(final String username, final int resultValue){
        final ResultEntity result = new ResultEntity();
        result.setUsername(username);
        result.setResultValue(resultValue);
        result.setDate(LocalDate.now());
        return resultRepository.save(result);
    }

    public List<ResultEntity> findAllByUsername(final String username){
        return resultRepository.findAllByUsername(username);
    }
}
