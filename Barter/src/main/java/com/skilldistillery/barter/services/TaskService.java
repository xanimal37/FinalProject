package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;

public interface TaskService {
	
	//basic CRUD
	Task createTask(Task task, int id);
	
	Task updateTask(Task task, int id);
	
	void deleteTask(int id);
	
	List<Task> getAllTasks();
	
	Task getTaskById(int id);
	
	//searching for task to do
	
	List<Task> getTasksNotOwnedByUser(User user);
	
	List<Task> getTasksBySkillNotOwnedByUser(User user,Skill skill);
	
	//handling user owned tasks
	
	List<Task> getTasksOwnedByUser(User user);
	

}
