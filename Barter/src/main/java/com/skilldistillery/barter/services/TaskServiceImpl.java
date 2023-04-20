package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.TaskRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

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
	public Task updateTask(Task task, int id, String username) {
		Task original = taskRepo.findById(id);
		if (original != null && task != null && original.getUser().getUsername().equals(username)) {
			original.setName(task.getName());
			original.setDescription(task.getDescription());
			original.setEstimatedHours(task.getEstimatedHours());
			original.setMaterialsProvided(task.getMaterialsProvided());
			original.setScheduleDate(task.getScheduleDate());
			original.setStartDate(task.getStartDate());
			original.setCompleteDate(task.getCompleteDate());
			original.setTaskStatus(task.getTaskStatus());
			original.setAddress(task.getAddress());
			original.setSkills(task.getSkills());
			// this method will see the id and know to update
			return taskRepo.saveAndFlush(original);
		}
		return null;
	}

	@Override
	public boolean deleteTask(int id) {
		// TODO Auto-generated method stub
		return false;

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
