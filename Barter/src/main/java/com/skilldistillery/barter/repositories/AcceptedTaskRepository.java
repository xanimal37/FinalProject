package com.skilldistillery.barter.repositories;

import java.util.List;

import com.skilldistillery.barter.entities.AcceptedTask;

public interface AcceptedTaskRepository {

	List<AcceptedTask> findByUser_Id(int id);
	
	List<AcceptedTask> findByTask_User_Username(String username);
}
