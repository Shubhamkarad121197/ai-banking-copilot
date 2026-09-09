package com.Shubham.ai_banking_copilot.controller;

import org.springframework.web.bind.annotation.*;

import com.Shubham.ai_banking_copilot.dto.LoginRequest;
import com.Shubham.ai_banking_copilot.dto.LoginResponseDTO;
import com.Shubham.ai_banking_copilot.dto.RegistrationRequest;
import com.Shubham.ai_banking_copilot.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegistrationRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}