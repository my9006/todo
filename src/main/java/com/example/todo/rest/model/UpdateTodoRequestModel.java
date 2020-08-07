package com.example.todo.rest.model;

import com.example.todo.persistence.TodoStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

public class UpdateTodoRequestModel {

	private String name;
	private TodoStatus status;

	public UpdateTodoRequestModel() {
	}

	public UpdateTodoRequestModel(String name, TodoStatus status) {
		this.name = name;
		this.status = status;
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
