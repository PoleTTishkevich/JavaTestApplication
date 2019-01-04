package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity, String> {

    List<ResultEntity> findAllByUsername(String username);
}
