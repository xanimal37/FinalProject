package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.TaskStatus;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.TaskStatusRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

	@Autowired
	private TaskStatusRepository taskStatusRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<TaskStatus> getAllTaskStatuses() {
		return taskStatusRepo.findAll();
	}

	@Override
	public TaskStatus getTaskStatusById(int id) {
		return taskStatusRepo.findById(id);
	}

	@Override
	public TaskStatus createTaskStatus(TaskStatus taskStatus, String username) {
		User user = userRepo.findByUsername(username);
		if (user.getRole().equals("admin")) {
			return taskStatusRepo.saveAndFlush(taskStatus);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteTaskStatus(int id, String username) {
		boolean wasDeleted = false;
		TaskStatus taskStatus = taskStatusRepo.findById(id);
		User user = userRepo.findByUsername(username);
		if (taskStatus != null && user.getRole().equals("admin")) {
			taskStatusRepo.delete(taskStatus);
			wasDeleted = true;
		}
		return wasDeleted;
	}

	@Override
	public TaskStatus updateTaskStatus(TaskStatus taskStatus, int id,String username) {
		User user = userRepo.findByUsername(username);
		TaskStatus original = taskStatusRepo.findById(id);
		if (original != null && taskStatus != null && user.getRole().equals("admin")) {
			original.setName(taskStatus.getName());
			original.setDescription(taskStatus.getDescription());
			// this method will see the id and know to update
			return taskStatusRepo.saveAndFlush(original);
		}
		return null;
	}

}
