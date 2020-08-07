package com.example.todo.rest.model;


public class CreateTodoRequestModel {
	private String name;

	public CreateTodoRequestModel() {
	}

	public CreateTodoRequestModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
