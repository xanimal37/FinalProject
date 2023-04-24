package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.AcceptedTask;
import com.skilldistillery.barter.entities.AcceptedTaskId;

public interface AcceptedTaskRepository extends JpaRepository<AcceptedTask, AcceptedTaskId>{

	List<AcceptedTask> findByAcceptedTaskId(AcceptedTaskId id);
	
	List<AcceptedTask> findByAcceptedTaskId_AcceptorId(int id);
	
	List<AcceptedTask> findByAcceptedTaskId_TaskId(int id);
}
