package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//all tasks 
	@GetMapping(path="tasks")
	List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	//add task
<<<<<<< HEAD
	@PostMapping(path="users/{id}/tasks")
	Task createTask(@RequestBody Task task, @PathVariable int id,HttpServletRequest req,HttpServletResponse res) {
=======
	//only for logged in user
	@PostMapping(path="tasks")
	Task createTask(@RequestBody Task task,Principal principal ,HttpServletRequest req,HttpServletResponse res) {
>>>>>>> b4763422f500497db03912a85c25c9bd80ceca9e
		try {
			task = taskService.createTask(task, principal.getName());
			res.setStatus(201);
			//StringBuffer url = req.getRequestURL(); // define as stringbuffer
			//url.append("/").append(task.getId()); // append id to url so will show user the post url
			//res.setHeader("Location", "http://localhost:8083"); // location
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			task=null;
		}
		return task;
	}
	
	//update task
<<<<<<< HEAD
	@PutMapping(path="tasks/{id}")
	Task updateTask(@PathVariable int id, @RequestBody Task task, HttpServletResponse res) {
		try {
			task = taskService.updateTask(task,id);
			if (task == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			task = null;
		}
		return task;
=======
	//only for logged in users task
		@PutMapping(path = "tasks/{id}")
		public Task updateTask(@PathVariable int id, Principal principal, @RequestBody Task task, HttpServletResponse res) {
			try {
				task = taskService.updateTask(task, id,principal.getName());
				if (task == null) {
					res.setStatus(404);
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				task = null;
			}
			return task;
		}
	
	//get all tasks owned by user
	//only for logged in user
	@GetMapping(path="users/tasks")
	List<Task> getAllTasksOwnedByUser(Principal principal){
		return taskService.getTasksOwnedByUser(principal.getName());
>>>>>>> b4763422f500497db03912a85c25c9bd80ceca9e
	}
	
}
