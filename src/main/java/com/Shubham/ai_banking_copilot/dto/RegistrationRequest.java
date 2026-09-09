package com.Shubham.ai_banking_copilot.dto;

import jakarta.validation.constraints.*;

public class RegistrationRequest {
	
	@NotBlank(message="Name is Required")
	private String name;
	
	@NotBlank(message="Email is Required")
	@Email(message="Please Enter a valid Email")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=6,message="Password must be more than 6 characters")
	private String password;
	
	public RegistrationRequest() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
