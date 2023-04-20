package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.entities.UserSkill;

public interface UserService {
	public List <User> getAllUsers();
	public User updateUserByAdmin(User user,int userId);
	public User updateAccount(User user,String username);
	public boolean deleteUser(int userId);
	public User findById(int userId);
	public User findByUsername(String username);
	public String addFriend(User user,User friend);
	public List<User> getUsersBySkill(String skillName);
}
