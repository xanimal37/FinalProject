package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.AcceptedTaskRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class AcceptedTaskServiceImpl implements AcceptedTaskService{

	@Autowired
	private AcceptedTaskRepository acceptedTaskRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<AcceptedTask> getAllAcceptedTasks() {
		return acceptedTaskRepo.findAll();
	}

	@Override
	public List<AcceptedTask> getAllUsersAcceptedTasks(String username) {
		User user = userRepo.findByUsername(username);
		if(user!=null) {
			return acceptedTaskRepo.findByAcceptedTaskId_AcceptorId(user.getId());
		}
		else {
			return null;
		}
		
	}

	@Override
	public AcceptedTask createAcceptedTask(AcceptedTask acceptedTask) {
		return acceptedTaskRepo.saveAndFlush(acceptedTask);
	}

	@Override
	public AcceptedTask updateAcceptedTask(AcceptedTask atask, int taskId, String username) {
		User user = userRepo.findByUsername(username);
		AcceptedTask original = acceptedTaskRepo.findByAcceptedTaskId_TaskIdAndAcceptedTaskId_AcceptorId(taskId,user.getId());
		
		if(original!=null && atask!=null) {
			
			original.setRemarksByRequestor(atask.getRemarksByRequestor());
			original.setRemarksByAcceptor(atask.getRemarksByAcceptor());
			original.setRatingByRequestor(atask.getRatingByRequestor());
			original.setRatingByAcceptor(atask.getRatingByAcceptor());
			original.setAcceptDate(atask.getAcceptDate());
			
			//this method will see the id and know to update
			return acceptedTaskRepo.saveAndFlush(original);
		}
		return null;
	}
	
}
