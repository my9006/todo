package com.example.todo.rest;

import com.example.todo.persistence.TodoItem;
import com.example.todo.persistence.repository.TodoRepository;
import com.example.todo.rest.converter.TodoConverter;
import com.example.todo.rest.model.CreateTodoRequestModel;
import com.example.todo.rest.model.TodoResponseModel;
import com.example.todo.rest.model.UpdateTodoRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jstat.Literal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.todo.persistence.TodoStatus.*;


@RestController
@RequestMapping(value = "/todo")
public class TodoController {
	//	-----> Dependency injection type 1
	//	@Autowired
	//	private TodoRepository todoRepository;

	//	-----> Dependency injection type 2
	//	private TodoRepository todoRepository;
	//	@Autowired
	//	public void setTodoRepository(TodoRepository todoRepository) {
	//		this.todoRepository = todoRepository;
	//	}

	//	-----> Type 3, in case of constructor no need to annotate @Autowired
	private final TodoRepository todoRepository;
	private final TodoConverter converter;

	public TodoController(TodoRepository todoRepository, TodoConverter converter) {
		this.todoRepository = todoRepository;
		this.converter = converter;
	}

	@PostMapping
	public ResponseEntity<TodoResponseModel> create(@RequestBody CreateTodoRequestModel createRequest) {
		TodoItem todoItem = new TodoItem(createRequest.getName(), NEW);
		TodoItem saveItem = todoRepository.save(todoItem);
		TodoResponseModel todoResponseModel = converter.convertEntityToResponseModel(saveItem);
		return ResponseEntity.ok(todoResponseModel);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TodoResponseModel> getById(@PathVariable(value = "id") Long id) {
		Optional<TodoItem> byId = todoRepository.findById(id);
		TodoResponseModel response = converter.convertEntityToResponseModel(byId.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<TodoResponseModel>> getAll() {
		List<TodoItem> all = todoRepository.findAll();
		List<TodoResponseModel> collect = all.stream().map(each -> converter.convertEntityToResponseModel(each)).collect(Collectors.toList());
		return ResponseEntity.ok(collect);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TodoResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody UpdateTodoRequestModel updateTodoRequestModel) {
		TodoItem item = todoRepository.findById(id).get();
		updateTodoItem(updateTodoRequestModel, item);
		return ResponseEntity.ok(converter.convertEntityToResponseModel(todoRepository.save(item)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") Long id) {
		todoRepository.deleteById(id);
		return ResponseEntity.ok(!todoRepository.existsById(id));
	}

	private void updateTodoItem(UpdateTodoRequestModel updateTodoRequestModel, TodoItem item) {

		if (updateTodoRequestModel.getName() == null) {
			item.setName(item.getName());
		} else if (updateTodoRequestModel.getName().isEmpty()) {
			item.setName(item.getName());
		} else {
			item.setName(updateTodoRequestModel.getName());
		}
		item.setStatus(updateTodoRequestModel.getStatus() == null ? item.getStatus() : updateTodoRequestModel.getStatus());
	}
}