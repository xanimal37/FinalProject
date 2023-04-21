package com.skilldistillery.barter.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.entities.SkillLevel;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.SkillRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SkillRepository skillRepo;
	
	@Override
	public User updateUserByAdmin(User user, int userId) {
		// TODO Auto-generated method stub
		User originalUser = userRepo.findById(userId);
		if (originalUser == null) {
			throw new IllegalArgumentException("User does not exist");
		}

		originalUser.setFirstname(user.getFirstname());
		originalUser.setLastname(user.getLastname());
		originalUser.setEmail(user.getEmail());
		originalUser.setImageURL(user.getImageURL());
		originalUser.setBiography(user.getBiography());
		if (user.getUsername() != null) {
			originalUser.setUsername(user.getUsername());
		}
		if (user.getRole() != null) {
			originalUser.setRole(user.getRole());
		}
		originalUser.setEnabled(user.isEnabled());

		if (user.getPassword() != null) {
			String encrypted = encoder.encode(user.getPassword());
			originalUser.setPassword(encrypted);
		}
		return userRepo.saveAndFlush(originalUser);

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		boolean deleted = false;
		User toDelete = userRepo.findById(userId);
		if (toDelete != null) {
			userRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public User findById(int userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId);

		if (user == null) {
			throw new IllegalArgumentException("User does not exist");
		}

		return user;

	}

	@Override
	public String addFriend(User user, User friend) {
		if (user.getFriends().contains(friend)) {
			return friend.getUsername() + " is already a friend of " + user.getUsername();
		}
		user.getFriends().add(friend);
		friend.getFriends().add(user);
		userRepo.saveAndFlush(user);
		userRepo.saveAndFlush(friend);
		return friend.getUsername() + " has been added as a friend of " + user.getUsername();

	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public User updateAccount(User user, String username) {
		// TODO Auto-generated method stub
		User originalUser = userRepo.findByUsername(username);
		if (originalUser == null) {
			throw new IllegalArgumentException("User does not exist");
		}

		originalUser.setFirstname(user.getFirstname());
		originalUser.setLastname(user.getLastname());
		originalUser.setEmail(user.getEmail());
		originalUser.setImageURL(user.getImageURL());
		originalUser.setBiography(user.getBiography());
		if (user.getUsername() != null) {
			originalUser.setUsername(user.getUsername());
		}

		originalUser.setEnabled(user.isEnabled());

		if (user.getPassword() != null) {
			String encrypted = encoder.encode(user.getPassword());
			originalUser.setPassword(encrypted);
		}
		return userRepo.saveAndFlush(originalUser);

	}

	@Override
	public List<User> getUsersBySkill(String skillName) {
		return userRepo.findBySkillsName(skillName);
	}

	@Override
	public long getUserCount() {
		// TODO Auto-generated method stub
		return userRepo.count();
	}

	@Override
	public List<User> getUsersBySkillLevel(String skillLevel) {
		// TODO Auto-generated method stub
		return userRepo.findBySkillLevel_Name(skillLevel);
	}

	@Override
	public List<User> getUsersBySkillLevelAndSkillName(String skillLevel, String skillName) {
		// TODO Auto-generated method stub
		return userRepo.findDistinctBySkillsNameAndSkillLevel_Name(skillName, skillLevel);
	}

	@Override
	public List<User> getUsersByRanking(String rankName) {
		// TODO Auto-generated method stub
		return userRepo.findByRankingName(rankName);
	}

	
	
}
