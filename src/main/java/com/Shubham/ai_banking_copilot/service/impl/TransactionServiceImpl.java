package com.Shubham.ai_banking_copilot.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.Shubham.ai_banking_copilot.dto.DepositRequestDTO;
import com.Shubham.ai_banking_copilot.dto.WithdrawRequestDTO;
import com.Shubham.ai_banking_copilot.entity.Account;
import com.Shubham.ai_banking_copilot.entity.Transaction;
import com.Shubham.ai_banking_copilot.entity.TransactionType;
import com.Shubham.ai_banking_copilot.repository.AccountRepository;
import com.Shubham.ai_banking_copilot.repository.TransactionRepository;
import com.Shubham.ai_banking_copilot.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	

    public TransactionServiceImpl(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

	@Override
	@Transactional
	public String deposit(DepositRequestDTO request) {
		//Find Account 
		Account account=accountRepository
				.findById(request.getAccountId())
				.orElseThrow(()->
				new RuntimeException("Account not Found")
						);
		
//		2. Update Account Balance
		
		account.setBalance(account.getBalance().add(request.getAmount()));
		
		accountRepository.save(account);
		
//		3. Create Transaction Record;
		
		  Transaction transaction = new Transaction();

	        transaction.setAccount(account);
	        transaction.setAmount(request.getAmount());
	        transaction.setTransactionType(TransactionType.DEPOSIT);
	        transaction.setDescription(request.getDescription());
	        transaction.setTransactionDate(LocalDateTime.now());

	        transactionRepository.save(transaction);
		return "Amount deposited successfully";
	}

	@Override
	@Transactional
	public String withdraw(WithdrawRequestDTO request) {

	    // 1. Find account
	    Account account = accountRepository
	            .findById(request.getAccountId())
	            .orElseThrow(() ->
	                    new RuntimeException("Account not found")
	            );

	    // 2. Check sufficient balance
	    if (account.getBalance()
	            .compareTo(request.getAmount()) < 0) {

	        throw new RuntimeException("Insufficient balance");
	    }

	    // 3. Deduct amount
	    account.setBalance(
	            account.getBalance()
	                    .subtract(request.getAmount())
	    );

	    // 4. Save updated account
	    accountRepository.save(account);

	    // 5. Create transaction record
	    Transaction transaction = new Transaction();

	    transaction.setAccount(account);
	    transaction.setAmount(request.getAmount());
	    transaction.setTransactionType(TransactionType.WITHDRAW);
	    transaction.setDescription(request.getDescription());
	    transaction.setTransactionDate(LocalDateTime.now());

	    transactionRepository.save(transaction);

	    return "Amount withdrawn successfully";
	}

}
