package com.example.Todo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
	private Long idd;
	private String title;
	private Date description;
	private boolean completed;
	

}
