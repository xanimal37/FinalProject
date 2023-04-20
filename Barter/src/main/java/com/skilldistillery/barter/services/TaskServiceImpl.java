package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;
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
	public Task createTask(Task task, int id) {
		User user = userRepo.findById(id);
		task.setUser(user);
		return taskRepo.saveAndFlush(task);
	}

	@Override
	public Task updateTask(Task task, int id) {
		Task original = taskRepo.findById(id);
		if(original!=null && task!=null) {
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
	public void deleteTask(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getTasksNotOwnedByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasksBySkillNotOwnedByUser(User user, Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasksOwnedByUser(User user) {
		// TODO Auto-generated method stub
		return null;
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
}
