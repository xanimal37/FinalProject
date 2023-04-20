package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.User;

public interface AuthService {

	public User register(User user);
	public User getUserByUsername(String username);

}
