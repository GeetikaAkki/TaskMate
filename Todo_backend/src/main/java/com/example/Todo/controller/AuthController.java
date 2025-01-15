package com.example.Todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Todo.dto.LoginDto;
import com.example.Todo.dto.RegisterDto;
import com.example.Todo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;
	 public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	  @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
	        String response = authService.register(registerDto);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }

	  @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
	        String response = authService.login(loginDto);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }


}
