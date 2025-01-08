package com.example.Todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Todo.dto.TodoDto;
import com.example.Todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
	private TodoService todoService;
	@PostMapping
	
	public ResponseEntity <TodoDto>addTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodo=todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
		
	}
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo( @PathVariable("id") Long todoId){
		TodoDto todoDto=todoService.getTodo(todoId);
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
		
			
		}
	@GetMapping
	public ResponseEntity<List<TodoDto>>getAllTodos(){
		List <TodoDto> todos=todoService.getAllTodos();
		return new ResponseEntity<>(todos,HttpStatus.OK);
		
	}
		
		
		
		
	}
	
	
	


