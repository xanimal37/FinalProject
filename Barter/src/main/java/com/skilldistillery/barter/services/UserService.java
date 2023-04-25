package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Address;
import com.skilldistillery.barter.entities.User;

public interface UserService {
	public List <User> getAllUsers();
	public boolean deleteUser(int userId);
	public User findById(int userId);
	public User findByUsername(String username);
	public String addFriend(User user,User friend);
	public List<User> getUsersBySkill(String skillName);
	public long getUserCount();
	public List<User> getUsersBySkillLevel(String skillLevel);
	public List<User> getUsersBySkillNameAndSkillLevel(String skillLevel,String skillName);
	public List<User> getUsersByRanking(String rankName);
	User updateAccount(User user, int id);
	User archiveUser( String username, int id);
	User unarchiveUser( String username, int id);
	public User updateUserByAdmin(User user,int userId);
	
	public User updateUserEmail(User user,int userId);
//	public User updateUserByAdmin(User user,int userId);
//	public User updateUserByAdmin(User user,int userId);
//	User updateEmail(User user, int userId);
	
	

	      
	
}

