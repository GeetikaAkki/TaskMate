package com.example.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Todo.entity.todo;

public interface todorepository extends JpaRepository<todo, Long > {

}
