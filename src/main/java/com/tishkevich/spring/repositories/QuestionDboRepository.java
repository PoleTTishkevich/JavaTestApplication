package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.Category;
import com.tishkevich.spring.entities.QuestionDbo;
import com.tishkevich.spring.entities.QuestionDto;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select q from QuestionDbo q where q.id in (:idOfSelectedRows) order by q.id, 1")
    List<QuestionDbo> findNecessary(@Param("idOfSelectedRows") Integer[] idOfSelectedRows);

    List<QuestionDbo> findAllByIdIn(Set<Integer> numbers);

    @Query(value = "select * from question where category_id =:categoryId LIMIT :startPosition, 10", nativeQuery = true)
    List<QuestionDbo> findAllByCategory (@Param("categoryId")int categoryId, @Param("startPosition") Long startPosition);

    @Query(value = "select * from question LIMIT :startPosition, :count", nativeQuery = true)
    List<QuestionDbo> findAllWithLimit (@Param("startPosition") Long startPosition, @Param("count") int count);

    @Query(value = "select * from question where category_id =:categoryId LIMIT :startPosition, :count", nativeQuery = true)
    List<QuestionDbo> findAllByCategoryWithLimit (@Param("categoryId")int categoryId, @Param("startPosition") Long startPosition, @Param("count") int count);


}
