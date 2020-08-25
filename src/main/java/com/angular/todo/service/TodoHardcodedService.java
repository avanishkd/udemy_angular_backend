package com.angular.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.angular.todo.Entity.Todo;

@Service
public class TodoHardcodedService {

	private static List<Todo> todos = new ArrayList();
	private static int idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, "Avanish", "Learn to play guitar", LocalDate.now(), false));
		todos.add(new Todo(++idCounter, "Avanish", "Learn angular", LocalDate.now(), false));
		todos.add(new Todo(++idCounter, "Avanish", "Learn microservices", LocalDate.now(), false));

	}

	public List<Todo> findAll() {
		return todos;
	}

	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null) {
			return null;
		}

		if (todos.remove(todo)) {
			
			idCounter=0;
			todos.stream().forEach(item->{item.setId(++idCounter);});
			Collections.sort(todos);
			return todo;
		}
		return null;
	}

	public Todo findById(long id) {
		for (Todo itrtodo : todos) {
			if (itrtodo.getId() == id)
				return itrtodo;
		}
		return null;
	}

	public Todo save(Todo todo) {
		if (todo.getId() == -1 || todo.getId()==0) {
			idCounter=todos.size();
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		Collections.sort(todos);
		return todo;
	}
}
