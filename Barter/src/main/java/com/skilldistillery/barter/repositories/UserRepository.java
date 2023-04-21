package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findById(int id);
	User findByIdAndUsername(int userId,String username);
	List<User> findByUserSkills_Skill_Name(String skillName);
	List<User> findByUserSkills_SkillLevel_Name(String skillLevel);
	
	
	List<User> findByUserSkills_Skill_NameAndUserSkills_SkillLevel_Name(String skillName,String skillLevel);
	
	List<User> findByRankingName(String name);
	
	
	



}
