package com.example.Todo.service;

import java.util.List;

import com.example.Todo.dto.TodoDto;

public interface TodoService {
	TodoDto addTodo(TodoDto todoDto);

	TodoDto getTodo(Long id);
	List<TodoDto> getAllTodos();
	
}
