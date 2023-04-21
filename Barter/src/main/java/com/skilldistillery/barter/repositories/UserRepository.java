package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.barter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findById(int id);
	User findByIdAndUsername(int userId,String username);
	List<User> findBySkillsName(String skillName);
	List<User> findBySkillLevel_Name(String skillLevel);
	
	
	List<User> findDistinctBySkillsNameAndSkillLevel_Name(String skillLevel,String skillName);
	
	List<User> findByRankingName(String name);
	
	
	



}
