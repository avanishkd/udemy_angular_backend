package com.angular.todo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.angular.todo.Entity.Todo;
import com.angular.todo.service.TodoHardcodedService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Resource {

	@Autowired
	private TodoHardcodedService todoService;

	@GetMapping("/users/{userName}/todos")
	private List<Todo> getAllTodos(@PathVariable String userName) {
		return todoService.findAll();
	}

	@GetMapping("/users/{userName}/todos/{id}")
	private Todo getTodo(@PathVariable String userName, @PathVariable long id) {
		return todoService.findById(id);
	}

	@DeleteMapping("/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id) {
		Todo todo = todoService.deleteById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/users/{userName}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id,
			@RequestBody Todo todo) {
		Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping("/users/{userName}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String userName, @RequestBody Todo todo) {
		Todo todoCreated = todoService.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoCreated.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
