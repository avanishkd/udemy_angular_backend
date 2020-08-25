package com.angular.todo.welcome.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.angular.todo.welcome.entity.HelloWorldBean;

@CrossOrigin(origins="http://localhost:5200")
@RestController
public class Controller {
	
	@GetMapping(path="/welcome/{welcomeString}")
	public HelloWorldBean welcomeMessage(@PathVariable String welcomeString) {
		
//		throw new RuntimeException("An error occured!");
		return new HelloWorldBean(String.format("Hello World, %s", welcomeString));
	}

}
