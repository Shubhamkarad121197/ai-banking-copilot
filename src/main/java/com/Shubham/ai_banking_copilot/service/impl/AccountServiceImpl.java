package com.Shubham.ai_banking_copilot.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Shubham.ai_banking_copilot.dto.AccountResponseDTO;
import com.Shubham.ai_banking_copilot.dto.CreateAccountRequest;
import com.Shubham.ai_banking_copilot.entity.Account;
import com.Shubham.ai_banking_copilot.entity.User;
import com.Shubham.ai_banking_copilot.repository.AccountRepository;
import com.Shubham.ai_banking_copilot.repository.UserRepository;
import com.Shubham.ai_banking_copilot.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(
            AccountRepository accountRepository,
            UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createAccount(CreateAccountRequest request) {

        // Get logged-in user's email
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        // Find user
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        // Create account
        Account account = new Account();

        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        accountRepository.save(account);

        return "Account created successfully";
    }


    @Override
    public List<AccountResponseDTO> getMyAccounts() {

        // Get logged-in user's email
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        // Find user
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        // Get user's accounts
        List<Account> accounts =
                accountRepository.findByUser(user);

        // Convert Account -> AccountResponseDTO
        return accounts.stream()
                .map(account -> new AccountResponseDTO(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getAccountType().name(),
                        account.getBalance()
                ))
                .toList();
    }


    private String generateAccountNumber() {

        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();
    }
}