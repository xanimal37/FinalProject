package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Skill;

public interface SkillRepository  extends JpaRepository<Skill, Integer> {
	Skill findByName(String name);
	Skill findById(int id);
	List<Skill> findByUserSkills_User_Username(String username);
	
	List<Skill> findByUserSkills_SkillLevelName(String name);
	
	
}
