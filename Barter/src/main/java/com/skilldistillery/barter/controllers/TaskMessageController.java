package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.TaskMessage;
import com.skilldistillery.barter.services.TaskMessageService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class TaskMessageController {

	@Autowired
	private TaskMessageService taskMessageService;
	
	@GetMapping(path="tasks/{id}/taskMessages")
	public List<TaskMessage> getTaskMessagesByTask(@PathVariable int id){
		return taskMessageService.getTaskMessagesByTask(id);
	}
	
	@GetMapping(path="users/taskMessages")
	public List<TaskMessage> getUsersTaskMessages(Principal principal){
		return taskMessageService.getTaskMessagesByUser(principal.getName());
	}
	
	@PostMapping(path="tasks/{id}/taskMessages")
	public TaskMessage createTaskMessage(@RequestBody TaskMessage taskMessage, @PathVariable int id, Principal principal,HttpServletResponse res) {
		try {
			taskMessage = taskMessageService.createTaskMessage(taskMessage, id,principal.getName());
			res.setStatus(201);
			//StringBuffer url = req.getRequestURL(); // define as stringbuffer
			//url.append("/").append(task.getId()); // append id to url so will show user the post url
			//res.setHeader("Location", "http://localhost:8083"); // location
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			taskMessage=null;
		}
		return taskMessage;
	}
}
