package com.tishkevich.spring.utils;

import com.tishkevich.spring.entities.ResultDto;
import com.tishkevich.spring.entities.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ResultConverter {

    public ResultDto convertToDto (final ResultEntity resultEntity){
        final ResultDto resultDto = new ResultDto();
        BeanUtils.copyProperties(resultEntity, resultDto);
        return resultDto;
    }

    public ResultEntity convertToEntity(final ResultDto resultDto){
        final ResultEntity resultEntity = new ResultEntity();
        BeanUtils.copyProperties(resultDto, resultEntity);
        return resultEntity;
    }
}
