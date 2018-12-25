package com.tishkevich.spring.repositories;

import com.tishkevich.spring.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
	UserAccount findByUsername(String username);
}
