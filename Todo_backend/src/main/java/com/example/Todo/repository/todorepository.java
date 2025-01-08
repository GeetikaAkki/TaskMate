package com.example.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Todo.entity.Todo;

public interface Todorepository extends JpaRepository<Todo, Long > {

}
