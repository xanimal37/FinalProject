package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.TaskStatus;

public interface TaskStatusService {

	List<TaskStatus> getAllTaskStatuses();
	
	TaskStatus getTaskStatusById(int id);
	
	TaskStatus createTaskStatus(TaskStatus taskStatus, String username);
	
	boolean deleteTaskStatus(int id, String username);
	
	TaskStatus updateTaskStatus(TaskStatus taskStatus, int id, String username);
	
}
