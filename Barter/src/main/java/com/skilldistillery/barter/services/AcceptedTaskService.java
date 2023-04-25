package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.entities.Task;

public interface AcceptedTaskService {
	
	List<AcceptedTask> getAllAcceptedTasks();
	
	List<AcceptedTask> getAllUsersAcceptedTasks(String username);
	
	AcceptedTask createAcceptedTask(AcceptedTask acceptedTask);
	
	AcceptedTask updateAcceptedTask(AcceptedTask atask, int taskid, String username);

}
