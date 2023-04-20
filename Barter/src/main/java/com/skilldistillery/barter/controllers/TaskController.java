package com.skilldistillery.barter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.services.TaskService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	//all tasks (for admin purposes)
	@GetMapping(path="tasks")
	List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	//add task
	@PostMapping(path="user/{id}/tasks")
	Task createTask(Task task, int id) {
		return null;
	}
}
