package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.TaskMessage;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.TaskMessageRepository;
import com.skilldistillery.barter.repositories.TaskRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class TaskMessageServiceImpl implements TaskMessageService{
	
	@Autowired
	private TaskMessageRepository taskMessageRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TaskRepository taskRepo;

	@Override
	public List<TaskMessage> getTaskMessagesByUser(String username) {
		return taskMessageRepo.findByUser_Username(username);
	}

	@Override
	public List<TaskMessage> getTaskMessagesByTask(int id) {
		return taskMessageRepo.findByTask_Id(id);
	}
	
	@Override
	public TaskMessage createTaskMessage(TaskMessage taskMessage,int id, String username) {
		User user = userRepo.findByUsername(username);
		Task task = taskRepo.findById(id);
		if(user!=null && task!=null) {
			taskMessage.setUser(user);
			taskMessage.setTask(task);
			return taskMessageRepo.saveAndFlush(taskMessage);
		}
		else {
			return null;
		}
	}
	
	
}
