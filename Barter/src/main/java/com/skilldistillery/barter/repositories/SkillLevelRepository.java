package com.skilldistillery.barter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.SkillLevel;

public interface SkillLevelRepository extends JpaRepository<SkillLevel, Integer> {
	
}
