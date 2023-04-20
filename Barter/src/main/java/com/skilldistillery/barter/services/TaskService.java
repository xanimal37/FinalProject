package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Task;


public interface TaskService {
	
	//basic CRUD
	Task getTaskById(int id);
	
	List<Task> getAllTasks();
	
	//authenticated
	Task createTask(Task task, String username);
	
	Task updateTask(Task task, int id, String username);
	
	boolean deleteTask(int id);
	
	List<Task> getTasksOwnedByUser(String username);
	

}
