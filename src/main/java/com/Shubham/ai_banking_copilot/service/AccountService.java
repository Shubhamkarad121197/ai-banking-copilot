package com.Shubham.ai_banking_copilot.service;

import java.util.List;

import com.Shubham.ai_banking_copilot.dto.AccountResponseDTO;
import com.Shubham.ai_banking_copilot.dto.CreateAccountRequest;
import com.Shubham.ai_banking_copilot.entity.Account;


public interface AccountService {
	String createAccount(CreateAccountRequest request);
	List<AccountResponseDTO> getMyAccounts();
	
	AccountResponseDTO getAccountDetails(Long accountId);
}
