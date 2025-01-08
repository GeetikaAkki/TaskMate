package com.example.Todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.Todo.repository.Todorepository;

import com.example.Todo.dto.TodoDto;
import com.example.Todo.entity.Todo;
import com.example.Todo.exception.ResourceNotFoundException;
import com.example.Todo.service.TodoService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
	private Todorepository todoRepository;
	private ModelMapper modelMapper;

	@Override
	
	public TodoDto addTodo( TodoDto todoDto) {
		Todo todo=modelMapper.map(todoDto, Todo.class);
		Todo savedTodo=todoRepository.save(todo);
		TodoDto savedTodoDto=modelMapper.map(savedTodo,  TodoDto.class);

		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo=todoRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Todo not found with id :"+id));
		return modelMapper.map(todo,  TodoDto.class);
		
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos=todoRepository.findAll();
		return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
		
	}

}
