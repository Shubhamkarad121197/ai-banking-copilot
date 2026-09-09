package com.Shubham.ai_banking_copilot.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Shubham.ai_banking_copilot.dto.LoginRequest;
import com.Shubham.ai_banking_copilot.dto.LoginResponseDTO;
import com.Shubham.ai_banking_copilot.dto.RegistrationRequest;
import com.Shubham.ai_banking_copilot.entity.User;
import com.Shubham.ai_banking_copilot.exception.UserAlreadyExistsException;
import com.Shubham.ai_banking_copilot.repository.UserRepository;
import com.Shubham.ai_banking_copilot.security.JwtService;
import com.Shubham.ai_banking_copilot.service.AuthService;



@Service
public class AuthServiceImpl implements AuthService {
	
	
	
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.jwtService=jwtService;
	}

	@Override
	public String register(RegistrationRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("Email already registered");
		}
		
		User user=new User();
		
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		
		userRepository.save(user);
		// TODO Auto-generated method stub
		return "User Registered Successfully";
	}

	
	@Override
	public LoginResponseDTO login(LoginRequest request) {

	    User user = userRepository
	            .findByEmail(request.getEmail())
	            .orElseThrow(() ->
	                    new RuntimeException("Invalid email or password")
	            );

	    if (!passwordEncoder.matches(
	            request.getPassword(),
	            user.getPassword())) {

	        throw new RuntimeException(
	                "Invalid email or password"
	        );
	    }

	    String token = jwtService.generateToken(user.getEmail());

	    return new LoginResponseDTO(token);
	}

}
