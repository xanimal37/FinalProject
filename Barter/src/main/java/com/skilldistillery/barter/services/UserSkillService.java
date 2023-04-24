package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Address;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.entities.UserSkill;

public interface UserSkillService {
	public List <UserSkill> getAllUserSkills();
	public UserSkill createNewUserSkill(UserSkill userSkill,String username);
	     
	
}

