package com.Shubham.ai_banking_copilot.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;



@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	
	private TransactionType transactionType;
	
	private String description;
	
	private LocalDateTime transactionDate;
	
	@ManyToOne
	
	private Account account;
	
	
	public Transaction() {
		
	}


	public Transaction(Long id, BigDecimal amount, TransactionType transactionType, String description,
			LocalDateTime transactionDate, Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.transactionType = transactionType;
		this.description = description;
		this.transactionDate = transactionDate;
		this.account = account;
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


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
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


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	

}
