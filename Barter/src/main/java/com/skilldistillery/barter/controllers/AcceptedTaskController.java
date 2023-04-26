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

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.services.AcceptedTaskService;
import com.skilldistillery.barter.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AcceptedTaskController {
	
	@Autowired
	private AcceptedTaskService acceptedTaskService;
	
	//test
	@GetMapping(path="acceptedTasks")
	public List<AcceptedTask> getAllAcceptedTasks(){
		return acceptedTaskService.getAllAcceptedTasks();
	}
	
	@GetMapping(path="users/acceptedTasks")
	public List<AcceptedTask> getAllUsersAcceptedTasks(Principal principal){
		return acceptedTaskService.getAllUsersAcceptedTasks(principal.getName());
	}
	
	@PostMapping(path="users/acceptedTasks")
	public AcceptedTask createAcceptedTask(@RequestBody AcceptedTask acceptedTask, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			acceptedTask = acceptedTaskService.createAcceptedTask(acceptedTask);
			res.setStatus(201);
			// StringBuffer url = req.getRequestURL(); // define as stringbuffer
			// url.append("/").append(task.getId()); // append id to url so will show user
			// the post url
			// res.setHeader("Location", "http://localhost:8083"); // location
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			acceptedTask = null;
		}
		return acceptedTask;
	}

	// update task
		// only for logged in users task
		@PutMapping(path = "users/acceptedTasks/{taskid}/{acceptorid}")
		public AcceptedTask updateAcceptedTask(@PathVariable int taskid,@PathVariable int acceptorid, Principal principal, @RequestBody AcceptedTask acceptedTask, HttpServletResponse res) {
			try {
				acceptedTask = acceptedTaskService.updateAcceptedTask(acceptedTask, taskid,acceptorid);
				if (acceptedTask == null) {
					res.setStatus(404);
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				acceptedTask = null;
			}
			return acceptedTask;
		}
}
