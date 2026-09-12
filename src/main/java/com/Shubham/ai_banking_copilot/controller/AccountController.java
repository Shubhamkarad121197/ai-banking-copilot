package com.Shubham.ai_banking_copilot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shubham.ai_banking_copilot.dto.AccountResponseDTO;
import com.Shubham.ai_banking_copilot.dto.ApiResponse;
import com.Shubham.ai_banking_copilot.dto.CreateAccountRequest;
import com.Shubham.ai_banking_copilot.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        String response = accountService.createAccount(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(response));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getMyAccounts() {

        return ResponseEntity.ok(
                accountService.getMyAccounts()
        );
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountDetails(
            @PathVariable Long accountId) {

        return ResponseEntity.ok(
                accountService.getAccountDetails(accountId)
        );
    }
}