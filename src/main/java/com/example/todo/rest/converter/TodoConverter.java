package com.example.todo.rest.converter;

import com.example.todo.persistence.TodoItem;
import com.example.todo.rest.model.TodoResponseModel;
import org.springframework.stereotype.Component;

@Component
public class TodoConverter {

	public TodoResponseModel convertEntityToResponseModel(TodoItem saveItem) {
		return new TodoResponseModel(saveItem.getId(), saveItem.getName(), saveItem.getStatus());
	}

}
