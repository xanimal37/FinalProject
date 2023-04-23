package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.AcceptedTask;

public interface AcceptedTaskService {
	
	List<AcceptedTask> getAllAcceptedTasks();
	
	List<AcceptedTask> getAllUsersAcceptedTasks(String username);
	
	AcceptedTask createAcceptedTask(AcceptedTask acceptedTask);
	

}
