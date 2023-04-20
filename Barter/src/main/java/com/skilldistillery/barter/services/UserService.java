package com.skilldistillery.barter.services;

import java.util.Set;

import com.skilldistillery.barter.entities.User;

public interface UserService {
	public Set <User> getAllUsers();
	public User updateUser(User user,int userId);
	public String changePassword(String username, String currentPassword, String newPassword);
	public User archiveUser(int id);
	public void deleteUser(int userId);
	public User unArchiveUser(int id);
}
