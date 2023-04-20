//package com.skilldistillery.barter.services;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.skilldistillery.barter.entities.User;
//import com.skilldistillery.barter.repositories.UserRepository;
//
//public class UserServiceImpl implements UserService {
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Autowired
//	private UserRepository userRepo;
//
//	@Override
//	public User updateUser(User user, int userId) {
//		// TODO Auto-generated method stub
//		User originalUser = userRepo.findById(userId);
//		if (originalUser == null) {
//			throw new IllegalArgumentException("User does not exist");
//		}
//		String encrypted = encoder.encode(user.getPassword());
//		originalUser.setFirstname(user.getFirstname());
//		originalUser.setLastname(user.getLastname());
//		originalUser.setEmail(user.getEmail());
//		originalUser.setBiography(user.getBiography());
//		originalUser.setPassword(encrypted);
//		return userRepo.saveAndFlush(originalUser);
//
//	}
//
//	@Override
//	public String changePassword(String username, String currentPassword, String newPassword) {
//		// TODO Auto-generated method stub
//		User user = userRepo.findByUsername(username);
//		if (user == null) {
//			throw new IllegalArgumentException("User does not exist");
//		}
//		String encryptedCurrentPassword = encoder.encode(currentPassword);
//		if (!user.getPassword().equals(encryptedCurrentPassword)) {
//			throw new IllegalArgumentException("Incorrect current password");
//		}
//		String encryptedNewPassword = encoder.encode(newPassword);
//		user.setPassword(encryptedNewPassword);
//		userRepo.save(user);
//
//		return encryptedNewPassword;
//	}
//
//	@Override
//	public User archiveUser(int userId) {
//		User user = userRepo.findById(userId);
//		if (user != null) {
//			user.setEnabled(false);
//			userRepo.saveAndFlush(user);
//
//		}
//		return user;
//
//	}
//	@Override
//	public User unArchiveUser(int userId) {
//		User user = userRepo.findById(userId);
//		if (user != null) {
//			user.setEnabled(true);
//			userRepo.saveAndFlush(user);
//
//		}
//		return user;
//		
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		return userRepo.findAll();
//	}
//
//	@Override
//	public void deleteUser(int userId) {
//		// TODO Auto-generated method stub
//		User user = userRepo.findById(userId);
//
//		if (user == null) {
//			throw new IllegalArgumentException("User does not exist");
//		}
//
//		userRepo.delete(user);
//
//	}
//
//}
