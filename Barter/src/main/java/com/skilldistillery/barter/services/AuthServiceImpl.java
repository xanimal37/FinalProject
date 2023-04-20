package com.skilldistillery.barter.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		// FIX ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		user.setEnabled(true);
		user.setRole("user");
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	

	

}
