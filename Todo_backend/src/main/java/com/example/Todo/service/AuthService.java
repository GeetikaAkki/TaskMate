package com.example.Todo.service;

import com.example.Todo.dto.JwtAuthResponse;
import com.example.Todo.dto.LoginDto;
import com.example.Todo.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);
		JwtAuthResponse login(LoginDto loginDto);
	

}
