package com.Shubham.ai_banking_copilot.service;

import com.Shubham.ai_banking_copilot.dto.LoginRequest;
import com.Shubham.ai_banking_copilot.dto.LoginResponseDTO;
import com.Shubham.ai_banking_copilot.dto.RegistrationRequest;

public interface AuthService {
	String register(RegistrationRequest request);
	
	LoginResponseDTO login(LoginRequest request);
	
}
