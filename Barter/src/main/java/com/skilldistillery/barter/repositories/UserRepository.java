package com.skilldistillery.barter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findById(int id);
	User findByIdAndUsername(int userId,String username);
	



}
