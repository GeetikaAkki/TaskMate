package com.example.Todo.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false, unique=true)
	private String email;
	@Column(nullable=false, unique=true)
	private String password;
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="users_roles", joinColumns= @JoinColumn (name="user_id", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))
	private Set<Role>roles;
	

}
