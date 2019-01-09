package com.tishkevich.spring.service;

import com.tishkevich.spring.entities.ResultDto;
import com.tishkevich.spring.entities.ResultEntity;
import com.tishkevich.spring.repositories.ResultRepository;
import com.tishkevich.spring.utils.ResultConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final ResultConverter resultConverter;

    public ResultDto saveResult(final String username, final int resultValue){
        final ResultEntity result = new ResultEntity();
        result.setUsername(username);
        result.setResultValue(resultValue);
        result.setDate(LocalDate.now());
        return resultConverter.convertToDto(resultRepository.save(result));
    }

    public List<ResultDto> getAllByUsername(final String username){
        return resultRepository.getAllByUsername(username).stream().map(resultConverter::convertToDto).collect(Collectors.toList());
    }
}
