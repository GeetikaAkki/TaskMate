package com.example.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
