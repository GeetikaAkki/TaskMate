package com.example.Todo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException {
	private HttpStatus status;
	private String message;
	
	

}
