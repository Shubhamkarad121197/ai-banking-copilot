package com.Shubham.ai_banking_copilot.service;

import com.Shubham.ai_banking_copilot.dto.DepositRequestDTO;
import com.Shubham.ai_banking_copilot.dto.WithdrawRequestDTO;

public interface TransactionService {
	String deposit(DepositRequestDTO request);
	
	String withdraw(WithdrawRequestDTO request);
}
