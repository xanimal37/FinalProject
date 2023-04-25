package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.AcceptedTaskRepository;
import com.skilldistillery.barter.repositories.TaskRepository;
import com.skilldistillery.barter.repositories.TaskStatusRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TaskStatusRepository taskStatusRepo;
	@Autowired
	private AcceptedTaskRepository acceptedTaskRepo;

	@Override
	public Task createTask(Task task, String username) {
		User user = userRepo.findByUsername(username);
		task.setTaskStatus(taskStatusRepo.findById(1));
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
			original.setSkills(task.getSkills());
			//task status will change depending on other factors
			//check if accepted
			List<AcceptedTask> acceptedTasksReferencingThisTask = acceptedTaskRepo.findByAcceptedTaskId_TaskId(task.getId());
			
			if (acceptedTasksReferencingThisTask==null) {
				task.setTaskStatus(taskStatusRepo.findById(1)); //pending - not accepted
			}
			else if(acceptedTasksReferencingThisTask!=null && task.getScheduleDate()==null) {
				task.setTaskStatus(taskStatusRepo.findById(2));
			}
			else if(task.getScheduleDate()!=null && task.getCompleteDate()==null) {
				task.setTaskStatus(taskStatusRepo.findById(3));
			}
			else if(task.getCompleteDate()!=null) {
				task.setTaskStatus(taskStatusRepo.findById(4));
			}
			//if accepted task ratings and comments are fulled it in is verified
			else if (
				acceptedTasksReferencingThisTask.get(0)!=null && 
						acceptedTasksReferencingThisTask.get(0).getRemarksByRequestor()!=null &&
						acceptedTasksReferencingThisTask.get(0).getRatingByRequestor()!=null) {
				task.setTaskStatus(taskStatusRepo.findById(5));
			}
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
