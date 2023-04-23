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
		task.setAddress(user.getAddress());
		task.setUser(user);
		return taskRepo.saveAndFlush(task);
	}

	@Override
	public Task updateTask(Task task, int id,String username) {
		Task original = taskRepo.findById(id);
		if(original!=null && task!=null && original.getUser().getUsername().equals(username)) {
			original.setName(task.getName());
			original.setDescription(task.getDescription());
			original.setMaterialsProvided(task.getMaterialsProvided());
			original.setEstimatedHours(task.getEstimatedHours());
			original.setScheduleDate(task.getScheduleDate());
			original.setCompleteDate(task.getCompleteDate());
			original.setAddress(task.getAddress());
			original.setTaskStatus(task.getTaskStatus());
			original.setSkills(task.getSkills());
			//this method will see the id and know to update
			return taskRepo.saveAndFlush(original);
		}
		return null;
		
	}

	@Override
	public boolean deleteTask(int id) {
		boolean wasDeleted = false;
		Task task = taskRepo.findById(id);
		if(task!=null) {
			taskRepo.delete(task);
			wasDeleted = true;
		}
		return wasDeleted;
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task getTaskById(int id) {
		return taskRepo.findById(id);
	}

	@Override
	public List<Task> getTasksOwnedByUser(String username) {
		return taskRepo.findByUser_Username(username);
	}

	@Override
	public List<Task> getAllTasksOfStatus(String name) {
		return taskRepo.findByTaskStatus_Name(name);
	}
}
