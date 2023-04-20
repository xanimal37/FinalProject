package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.TaskMessage;

public interface TaskMessageService {

	List<TaskMessage> getTaskMessagesByUser(String username);
	
	List<TaskMessage> getTaskMessagesByTask(int id);
	
	TaskMessage createTaskMessage(TaskMessage taskMessage, int id, String username);
}
