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
	
	//test
	@GetMapping(path="acceptedTasks")
	public List<AcceptedTask> getAllAcceptedTasks(){
		return acceptedTaskService.getAllAcceptedTasks();
	}
	
	@GetMapping(path="users/acceptedTasks")
	public List<AcceptedTask> getAllUsersAcceptedTasks(Principal principal){
		return acceptedTaskService.getAllUsersAcceptedTasks(principal.getName());
	}

}
