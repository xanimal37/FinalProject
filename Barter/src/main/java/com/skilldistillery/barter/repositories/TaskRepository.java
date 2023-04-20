package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	Task findById(int id);
	
	//owner user
	List<Task> findByUser_Username(String username);
	
	

}
