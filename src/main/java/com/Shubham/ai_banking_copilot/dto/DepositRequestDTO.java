package com.Shubham.ai_banking_copilot.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;



public class DepositRequestDTO {
	@NotNull(message="Account Id is Required")
	private Long accountId;
	
	@NotNull(message="Amount is Required")
	@DecimalMin(
			value="0.01",
			message="Amount must be greater than 0")
	private BigDecimal amount;
	
	String description;
	
	public DepositRequestDTO() {
    }
	public Long getAccountId() {
		return accountId;
	}
	 public void setAccountId(Long accountId) {
	        this.accountId = accountId;
	    }


	    public BigDecimal getAmount() {
	        return amount;
	    }


	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }


	    public String getDescription() {
	        return description;
	    }


	    public void setDescription(String description) {
	        this.description = description;
	    }

}
