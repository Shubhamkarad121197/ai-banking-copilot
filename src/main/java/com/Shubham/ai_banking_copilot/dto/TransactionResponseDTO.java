package com.Shubham.ai_banking_copilot.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
	
	   private Long id;

	    private BigDecimal amount;

	    private String transactionType;

	    private String description;

	    private LocalDateTime transactionDate;
	    
	    public TransactionResponseDTO() {
	    	
	    }
	    
	    public TransactionResponseDTO(Long id,BigDecimal amount,String transactionType,String description,LocalDateTime transactionDate) {
	    	this.id=id;
	    	this.amount=amount;
	    	this.transactionType=transactionType;
	    	this.description=description;
	    	this.transactionDate=transactionDate;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public String getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDateTime getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(LocalDateTime transactionDate) {
			this.transactionDate = transactionDate;
		}
	    
	    

}
