package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.User;

public interface UserService {
	public List <User> getAllUsers();
	public User updateUser(User user,int userId);
	public String changePassword(String username, String currentPassword, String newPassword);
	public User archiveUser(int id);
	public void deleteUser(int userId);
}
