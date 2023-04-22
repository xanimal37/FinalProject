package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;

public interface SkillService {

	List<Skill> getSkillByUser(String username);
	
	List<Skill> getSkillBySkillLevelName(String name);
	
	List<Skill> getSkillByTaskName(String task);
	
    List<Skill> getAllSkillsByTaskId(int taskId);
    
    List<Skill> getAllSkills();
    
    Skill createSkill(Skill skill) ;

	Skill updateSkill(int skillId, Skill skillDetails);	
	public void deleteSkill(int skillId);

	Skill SkillbyId(int skillId);
}
    
