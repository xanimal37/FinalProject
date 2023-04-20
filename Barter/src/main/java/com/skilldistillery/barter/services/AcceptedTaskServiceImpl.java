package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.repositories.AcceptedTaskRepository;

@Service
public class AcceptedTaskServiceImpl implements AcceptedTaskService{

	@Autowired
	private AcceptedTaskRepository acceptedTaskRepo;

	@Override
	public List<AcceptedTask> getAllAcceptedTasks() {
		return acceptedTaskRepo.findAll();
	}
}
