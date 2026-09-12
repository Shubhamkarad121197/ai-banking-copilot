package com.Shubham.ai_banking_copilot.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransferRequestDTO {
	
	@NotNull(message="Sender account id is required")
	private Long senderAccountId;
	
	@NotNull(message="Reciever id is Required")
	private Long receiverAccountId;
	
	@DecimalMin(
			value="0.01",
			message="amount should be greater than 0"
			)
	private BigDecimal amount;
	
	private String description;
	
	public TransferRequestDTO() {
		
	}
	
	public Long getSenderAccountId() {
		return senderAccountId;
	}
	
	public void  setSenderAccountId(Long senderAccountId) {
		this.senderAccountId=senderAccountId;
	}

	public Long getReceiverAccountId() {
	    return receiverAccountId;
	}

	public void setReceiverAccountId(Long receiverAccountId) {
	    this.receiverAccountId = receiverAccountId;
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
