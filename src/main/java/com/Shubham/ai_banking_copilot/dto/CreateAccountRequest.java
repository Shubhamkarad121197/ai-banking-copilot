package com.Shubham.ai_banking_copilot.dto;

import com.Shubham.ai_banking_copilot.entity.AccountType;

import jakarta.validation.constraints.NotNull;

public class CreateAccountRequest {
	@NotNull(message="Account Type is Required")
	private AccountType accountType;
	
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType=accountType;
	}
}
