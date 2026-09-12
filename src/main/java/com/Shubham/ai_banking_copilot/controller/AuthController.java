package com.Shubham.ai_banking_copilot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Shubham.ai_banking_copilot.dto.ApiResponse;
import com.Shubham.ai_banking_copilot.dto.LoginRequest;
import com.Shubham.ai_banking_copilot.dto.LoginResponseDTO;
import com.Shubham.ai_banking_copilot.dto.RegistrationRequest;
import com.Shubham.ai_banking_copilot.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegistrationRequest request) {

        String response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(response));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}