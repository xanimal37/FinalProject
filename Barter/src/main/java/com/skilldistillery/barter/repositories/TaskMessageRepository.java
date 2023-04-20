package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.TaskMessage;

public interface TaskMessageRepository extends JpaRepository<TaskMessage, Integer>{
	
	List<TaskMessage> findByUser_Username(String username);
	
	List<TaskMessage> findByTask_Id(int id);

}
