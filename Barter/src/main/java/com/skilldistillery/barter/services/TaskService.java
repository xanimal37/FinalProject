package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Task;


public interface TaskService {
	
	//basic CRUD
	Task getTaskById(int id);
	
<<<<<<< HEAD
	//searching for task to do
	
	List<Task> getTasksNotOwnedByUser(User user);
=======
	List<Task> getAllTasks();
	
	//authenticated
	Task createTask(Task task, String username);
>>>>>>> b4763422f500497db03912a85c25c9bd80ceca9e
	
	Task updateTask(Task task, int id, String username);
	
	boolean deleteTask(int id);
	
	List<Task> getTasksOwnedByUser(String username);
	

}
