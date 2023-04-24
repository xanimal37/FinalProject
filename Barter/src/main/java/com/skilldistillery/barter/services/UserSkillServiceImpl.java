package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.entities.UserSkill;
import com.skilldistillery.barter.entities.UserSkillID;
import com.skilldistillery.barter.repositories.SkillRepository;
import com.skilldistillery.barter.repositories.UserRepository;
import com.skilldistillery.barter.repositories.UserSkillRepository;
@Service
public class UserSkillServiceImpl implements UserSkillService {
	@Autowired
	private UserSkillRepository userSkillRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SkillRepository skillRepo;
	
	@Override
	public List<UserSkill> getAllUserSkills() {
		// TODO Auto-generated method stub
		return userSkillRepo.findAll();
	}

	@Override
	public UserSkill createNewUserSkill(UserSkill userSkill, String username) {
		User user = userRepo.findByUsername(username);
		userSkill.setUser(user);
		UserSkillID userSkillId = new UserSkillID(user.getId(),userSkill.getSkill().getId());
		userSkill.setId(userSkillId);
		return userSkillRepo.saveAndFlush(userSkill);
	}

}
