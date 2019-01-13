package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    @Query(value = "select * from user_account where username LIKE :filterfrase LIMIT :startPosition, :count", nativeQuery = true)
    List<UserAccount> findAllWithLimitAndFilter(@Param("startPosition") Long startPosition, @Param("count") int count, @Param("filterfrase") String filterfrase);

    @Query(value = "select * from user_account LIMIT :startPosition, :count", nativeQuery = true)
    List<UserAccount> findAllWithLimit(@Param("startPosition") Long startPosition, @Param("count") int count);

    @Query(value = "select count(*) from user_account where username LIKE :filterfrase", nativeQuery = true)
    Long countWithFilter(@Param("filterfrase") String filterfrase);
}
