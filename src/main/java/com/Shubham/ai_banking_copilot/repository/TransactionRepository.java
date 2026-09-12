package com.Shubham.ai_banking_copilot.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Shubham.ai_banking_copilot.entity.Account;
import com.Shubham.ai_banking_copilot.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	List<Transaction> findByAccount(Account account);
	
	List<Transaction> findByAccountOrderByTransactionDateDesc(Account account);
	

}
