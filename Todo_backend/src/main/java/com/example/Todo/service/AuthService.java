package com.example.Todo.service;

import com.example.Todo.dto.LoginDto;
import com.example.Todo.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);
		String login(LoginDto loginDto);
	

}
