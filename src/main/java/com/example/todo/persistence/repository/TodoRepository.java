package com.example.todo.persistence.repository;

import com.example.todo.persistence.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

	void findAllByNameAndStatusOrderByName();

}