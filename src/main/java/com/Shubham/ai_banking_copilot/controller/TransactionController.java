package com.Shubham.ai_banking_copilot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shubham.ai_banking_copilot.dto.ApiResponse;
import com.Shubham.ai_banking_copilot.dto.DepositRequestDTO;
import com.Shubham.ai_banking_copilot.dto.TransactionResponseDTO;
import com.Shubham.ai_banking_copilot.dto.TransferRequestDTO;
import com.Shubham.ai_banking_copilot.dto.WithdrawRequestDTO;
import com.Shubham.ai_banking_copilot.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(
            @Valid @RequestBody DepositRequestDTO request) {

        String response =
                transactionService.deposit(request);

        return ResponseEntity.ok(
                new ApiResponse(response)
        );
    }


    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(
            @Valid @RequestBody WithdrawRequestDTO request) {

        String response =
                transactionService.withdraw(request);

        return ResponseEntity.ok(
                new ApiResponse(response)
        );
    }


    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(
            @Valid @RequestBody TransferRequestDTO request) {

        String response =
                transactionService.transfer(request);

        return ResponseEntity.ok(
                new ApiResponse(response)
        );
    }


    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>>
            getTransactionHistory(
                    @PathVariable Long accountId) {

        return ResponseEntity.ok(
                transactionService
                        .getTransactionHistory(accountId)
        );
    }
}