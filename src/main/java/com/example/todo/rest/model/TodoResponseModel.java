package com.example.todo.rest.model;

import com.example.todo.persistence.TodoStatus;

public class TodoResponseModel {
	private Long id;
	private String name;
	private TodoStatus status;

	public TodoResponseModel(Long id, String name, TodoStatus status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TodoStatus getStatus() {
		return status;
	}

	public void setStatus(TodoStatus status) {
		this.status = status;
	}
}
