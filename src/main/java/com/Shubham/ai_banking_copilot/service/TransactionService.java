package com.Shubham.ai_banking_copilot.service;

import java.util.List;

import com.Shubham.ai_banking_copilot.dto.DepositRequestDTO;
import com.Shubham.ai_banking_copilot.dto.TransferRequestDTO;
import com.Shubham.ai_banking_copilot.dto.WithdrawRequestDTO;
import com.Shubham.ai_banking_copilot.dto.TransactionResponseDTO;

public interface TransactionService {
	String deposit(DepositRequestDTO request);
	
	String withdraw(WithdrawRequestDTO request);
	
	String transfer(TransferRequestDTO request);
	
	
	List<TransactionResponseDTO> getTransactionHistory(Long accountId);
	
}
