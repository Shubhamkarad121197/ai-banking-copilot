package com.Shubham.ai_banking_copilot.dto;

import java.math.BigDecimal;

public class AccountResponseDTO {
	private Long id;
	
	private String accountNumber;
	
	private String accountType;
	
	private BigDecimal balance;
	
	public AccountResponseDTO() {
		
	}
	
	public AccountResponseDTO(Long id,String accountNumber,String accountType, BigDecimal balance) {
		this.id=id;
		this.accountNumber=accountNumber;
		this.accountType=accountType;
		this.balance=balance;
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
