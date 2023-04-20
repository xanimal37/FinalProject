package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.services.AcceptedTaskService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AcceptedTaskController {
	
	@Autowired
	private AcceptedTaskService acceptedTaskService;
	
	//get all of a users accepted tasks
	
	@GetMapping(path="users/acceptedTasks")
	public List<AcceptedTask> getAllAcceptedTasks(Principal principal){
		return acceptedTaskService.getAllAcceptedTasks(principal.getName());
	}

}
