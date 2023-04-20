package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findById(int id);
	User findByIdAndUsername(int userId,String username);
	List<User> findBySkillsName(String skillName);
	
	



}
