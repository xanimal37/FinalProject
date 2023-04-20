package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	Task findById(int id);
	
	//owner user
	List<Task> findByUser_Username(String username);
	
	List<Task> findByTaskStatus_Name(String name);
	
	
}
