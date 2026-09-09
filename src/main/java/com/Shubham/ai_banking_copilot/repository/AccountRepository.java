package com.Shubham.ai_banking_copilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shubham.ai_banking_copilot.entity.Account;
import com.Shubham.ai_banking_copilot.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUser(User user);

    boolean existsByAccountNumber(String accountNumber);

}