package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.TaskRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public Task createTask(Task task, String username) {
		User user = userRepo.findByUsername(username);
		task.setUser(user);
		return taskRepo.saveAndFlush(task);
	}

	@Override
	public Task updateTask(Task task, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTask(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task getTaskById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Task> getTasksOwnedByUser(String username) {
		return taskRepo.findByUser_Username(username);	
	}
}
