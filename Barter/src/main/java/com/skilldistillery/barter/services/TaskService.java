package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Task;


public interface TaskService {
	
	//basic CRUD
	Task createTask(Task task, String username);
	
	Task updateTask(Task task, int id);
	
	void deleteTask(int id);
	
	List<Task> getAllTasks();
	
	Task getTaskById(int id);

	
	//handling user owned tasks
	
	List<Task> getTasksOwnedByUser(String username);
	

}
