package com.skilldistillery.barter.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.AddressRepository;
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
	
	@Autowired
	private AddressRepository addressRepo;
	
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
//		if (user.getFriends().contains(friend)) {
//			return friend.getUsername() + " is already a friend of " + user.getUsername();
//		}
//		user.getFriends().add(friend);
//		friend.getFriends().add(user);
		user.addFriend(friend);
		userRepo.saveAndFlush(user);
		return friend.getUsername() + " has been added as a friend of " + user.getUsername();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public User updateAccount(User user,int id) {
		// TODO Auto-generated method stub
		User originalUser = userRepo.findById(id);
		if (originalUser == null) {
			throw new IllegalArgumentException("User does not exist");
		}
		if(user.getAddress() != null) {
		originalUser.setAddress(addressRepo.saveAndFlush(user.getAddress()));
		}
		originalUser.setFirstname(user.getFirstname());
		originalUser.setLastname(user.getLastname());
		originalUser.setEmail(user.getEmail());
		originalUser.setImageURL(user.getImageURL());
		originalUser.setBiography(user.getBiography());
		originalUser.setAvailability(user.getAvailability());
		originalUser.setPhone(user.getPhone());
		if (user.getUsername() != null) {
			originalUser.setUsername(user.getUsername());
		}

		originalUser.setEnabled(user.isEnabled());

//		if (user.getPassword() != null) {
//			String encrypted = encoder.encode(user.getPassword());
//			originalUser.setPassword(encrypted);
//		}
		return userRepo.saveAndFlush(originalUser);

	}

	@Override
	public List<User> getUsersBySkill(String skillName) {
		return userRepo.findByUserSkills_Skill_Name(skillName);
	}

	@Override
	public long getUserCount() {
		// TODO Auto-generated method stub
		return userRepo.count();
	}

	@Override
	public List<User> getUsersBySkillLevel(String skillLevel) {
		// TODO Auto-generated method stub
		return userRepo.findByUserSkills_SkillLevel_Name(skillLevel);
	}

	@Override
	public List<User> getUsersBySkillNameAndSkillLevel(String skillLevel, String skillName) {
		// TODO Auto-generated method stub
		return userRepo.findByUserSkills_Skill_NameAndUserSkills_SkillLevel_Name(skillName, skillLevel);
	}

	@Override
	public List<User> getUsersByRanking(String rankName) {
		// TODO Auto-generated method stub
		return userRepo.findByRankingName(rankName);
	}
	
	@Override
	public User archiveUser(String username, int id) {
		User user = userRepo.findById(id);
		User admin = userRepo.findByUsername(username);
		if (user!=null && admin.getRole().equals("admin")){
			user.setEnabled(false);

		}
		userRepo.saveAndFlush(user);
		return user;

	}
	@Override
	public User unarchiveUser(String username, int id) {
		User user = userRepo.findById(id);
		User admin = userRepo.findByUsername(username);
		if (user!=null && admin.getRole().equals("admin")) {
			user.setEnabled(true);

		}
		userRepo.saveAndFlush(user);
		return user;
		
	}

	
	


	public User updateUserEmail(User user, int userId) {
		// TODO Auto-generated method stub
		User originalUser = userRepo.findById(userId);
		originalUser.setEmail(user.getEmail());
		return null;
	}

}
