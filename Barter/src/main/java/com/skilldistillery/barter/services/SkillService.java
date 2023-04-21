package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;

public interface SkillService {

	List<Skill> getSkillByUser(String username);
	
	List<Skill> getSkillBySkillLevelName(String name);
	
}
