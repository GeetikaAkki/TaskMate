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
	 @Override
	    public TodoDto updateTodo(TodoDto todoDto, Long id) {

	         Todo todo = todoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
	         todo.setTitle(todoDto.getTitle());
	         todo.setDescription(todoDto.getDescription());
	         todo.setCompleted(todoDto.isCompleted());

	         Todo updatedTodo = todoRepository.save(todo);

	        return modelMapper.map(updatedTodo, TodoDto.class);
	    }
	 @Override
	    public void deleteTodo(Long id) {

	        Todo todo = todoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

	        todoRepository.deleteById(id);
	    }

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo=todoRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("Todo not found with id: "+id));
		todo.setCompleted(Boolean.TRUE);
		Todo updatedTodo=todoRepository.save(todo);
		
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}
	 @Override
	    public TodoDto inCompleteTodo(Long id) {

	        Todo todo = todoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

	        todo.setCompleted(Boolean.FALSE);

	        Todo updatedTodo = todoRepository.save(todo);

	        return modelMapper.map(updatedTodo, TodoDto.class);
	    }
	 



}
