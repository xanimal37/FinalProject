package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	//tasks by skill
	List<Task> findBySkill_Name(String name);
	
	//all tasks by user
	List<Task> findByUser_Username(String username);
	
	

}
