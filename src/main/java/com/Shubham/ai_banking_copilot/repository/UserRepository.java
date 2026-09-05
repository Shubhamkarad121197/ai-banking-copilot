package com.Shubham.ai_banking_copilot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shubham.ai_banking_copilot.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);

}
