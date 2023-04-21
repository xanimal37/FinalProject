package com.skilldistillery.barter.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.repositories.SkillRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SkillRepository skillRepo;
	
	@Override
	public List<Skill> getSkillByUser(String username) {
		// TODO Auto-generated method stub
		return skillRepo.findByUser_Username(username);
	}

	@Override
	public List<Skill> getSkillBySkillLevelName(String level) {
		// TODO Auto-generated method stub
		return skillRepo.findBySkillLevelName(level);
	}

	

}
