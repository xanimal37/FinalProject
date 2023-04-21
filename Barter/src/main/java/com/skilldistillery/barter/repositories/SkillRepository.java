package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.entities.TaskMessage;

public interface SkillRepository  extends JpaRepository<Skill, Integer> {
	Skill findByName(String name);
	List<Skill> findByUser_Username(String username);
	
	List<Skill> findBySkillLevelName(String name);
}
