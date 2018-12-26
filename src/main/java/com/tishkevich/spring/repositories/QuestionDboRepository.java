package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionDboRepository extends CrudRepository<QuestionDbo, Integer> {

    List<QuestionDbo> findAllByCategory(Category category);

    long countByCategory(Category category);

    @Query(value = "SELECT * FROM question LIMIT :limit,1", nativeQuery = true)
    QuestionDbo findNecessary(@Param("limit") int limit);

    @Query(value = "SELECT * FROM question WHERE category_id=:category_id LIMIT :limit,1", nativeQuery = true)
    QuestionDbo findNecessaryFromCategory(@Param("category_id") int categoryId, @Param("limit") int limit);

    List<QuestionDbo> findAllByIdIn(Set<Integer> numbers);
}
