package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.AcceptedTask;

public interface AcceptedTaskService {
	
	List<AcceptedTask> getAllAcceptedTasks(String username);

}
