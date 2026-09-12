package com.Shubham.ai_banking_copilot.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Shubham.ai_banking_copilot.dto.DepositRequestDTO;
import com.Shubham.ai_banking_copilot.dto.TransactionResponseDTO;
import com.Shubham.ai_banking_copilot.dto.TransferRequestDTO;
import com.Shubham.ai_banking_copilot.dto.WithdrawRequestDTO;
import com.Shubham.ai_banking_copilot.entity.Account;
import com.Shubham.ai_banking_copilot.entity.Transaction;
import com.Shubham.ai_banking_copilot.entity.TransactionType;
import com.Shubham.ai_banking_copilot.exception.BadRequestException;
import com.Shubham.ai_banking_copilot.exception.ResourceNotFoundException;
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
				new ResourceNotFoundException("Account not Found")
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
	                    new ResourceNotFoundException("Account not found")
	            );

	    // 2. Check sufficient balance
	    if (account.getBalance()
	            .compareTo(request.getAmount()) < 0) {

	        throw new BadRequestException("Insufficient balance");
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

	@Override
	@Transactional
	public String transfer(TransferRequestDTO request) {
		//Prevent Transferring to same account
		if(request.getSenderAccountId().equals(request.getReceiverAccountId())) {
			throw new BadRequestException(
					"Sender and reciever account cannot be same");
		}
		
		
		// 2. Find sender account
	    Account senderAccount = accountRepository
	            .findById(request.getSenderAccountId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Sender account not found")
	            );

	    // 3. Find receiver account
	    Account receiverAccount = accountRepository
	            .findById(request.getReceiverAccountId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Receiver account not found")
	            );
	    
	    
	    // 4.Check Sufficient Balance
	    if(senderAccount.getBalance().compareTo(request.getAmount())<0) {
	    	throw new BadRequestException("Insufficient balance");
	    }
	    
	 // 5. Deduct money from sender
	    senderAccount.setBalance(
	            senderAccount.getBalance()
	                    .subtract(request.getAmount())
	    );
	    
	    // 6. Add money to receiver
	    receiverAccount.setBalance(
	            receiverAccount.getBalance()
	                    .add(request.getAmount())
	    );
	    
	    // 7. Save both accounts
	    accountRepository.save(senderAccount);
	    accountRepository.save(receiverAccount);
	    		
	 // 8. Create sender transaction record
	    Transaction senderTransaction = new Transaction();

	    senderTransaction.setAccount(senderAccount);
	    senderTransaction.setAmount(request.getAmount());
	    senderTransaction.setTransactionType(TransactionType.TRANSFER);
	    senderTransaction.setDescription(
	            "Transfer sent: " + request.getDescription()
	    );
	    senderTransaction.setTransactionDate(LocalDateTime.now());

	    transactionRepository.save(senderTransaction);
	    
	    
	    // 9. Create receiver transaction record
	    Transaction receiverTransaction = new Transaction();

	    receiverTransaction.setAccount(receiverAccount);
	    receiverTransaction.setAmount(request.getAmount());
	    receiverTransaction.setTransactionType(TransactionType.TRANSFER);
	    receiverTransaction.setDescription(
	            "Transfer received: " + request.getDescription()
	    );
	    receiverTransaction.setTransactionDate(LocalDateTime.now());

	    transactionRepository.save(receiverTransaction);
	    
		return "Money Transfer Successfully";
	}

	@Override
	public List<TransactionResponseDTO> getTransactionHistory(Long accountId) {
		//find Account
		
		Account account = accountRepository.findById(accountId).orElseThrow(()->
			new ResourceNotFoundException("Account not found")
				);
		
		
		  // 2. Get all transactions for this account
		
		
	    List<Transaction> transactions =
	    		transactionRepository
	            .findByAccountOrderByTransactionDateDesc(account);
	    
	    
	    // 3. Convert Transaction Entity → TransactionResponseDTO
	    return transactions.stream()
	            .map(transaction ->
	                    new TransactionResponseDTO(

	                            transaction.getId(),

	                            transaction.getAmount(),

	                            transaction.getTransactionType()
	                                    .name(),

	                            transaction.getDescription(),

	                            transaction.getTransactionDate()

	                    )
	            )
	            .toList();
		
	}
	
	


}
