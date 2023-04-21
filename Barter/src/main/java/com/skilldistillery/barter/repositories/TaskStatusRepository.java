package com.skilldistillery.barter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {

	TaskStatus findById(int id);
}
