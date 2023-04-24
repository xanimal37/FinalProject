package com.skilldistillery.barter.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.entities.SkillLevel;
import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.repositories.SkillLevelRepository;
import com.skilldistillery.barter.repositories.SkillRepository;
import com.skilldistillery.barter.repositories.TaskRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private SkillLevelRepository skillLevelRepo;
	
	
	@Override
	public List<Skill> getSkillByUser(String username) {
		// TODO Auto-generated method stub
		return skillRepo.findByUserSkills_User_Username(username);
	}

	@Override
	public List<Skill> getSkillBySkillLevelName(String level) {
		// TODO Auto-generated method stub
		return skillRepo.findByUserSkills_SkillLevelName(level);
	}

	@Override
	public List<Skill> getSkillByTaskName(String task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> getAllSkillsByTaskId(int taskId) {
		Task task = taskRepo.findById(taskId);
		return task.getSkills();
	}
	@Override
	public Skill createSkill(Skill skill) {
        return skillRepo.saveAndFlush(skill);
    }
	@Override
	public List<Skill> getAllSkills() {
        return skillRepo.findAll();
    }
	@Override
	public Skill updateSkill(int skillId, Skill skillDetails) {
        Skill skill = skillRepo.findById(skillId);
        skill.setName(skillDetails.getName());
        skill.setDescription(skillDetails.getDescription());

        return skillRepo.saveAndFlush(skill);
    }
	@Override
	public void deleteSkill(int skillId) {
        Skill skill = skillRepo.findById(skillId);
              
        skillRepo.delete(skill);
    }
	@Override
	public Skill SkillbyId(int skillId) {
        Skill skill = skillRepo.findById(skillId);
        return skill;
    }

	@Override
	public List<SkillLevel> getAllSkillLevels() {
		// TODO Auto-generated method stub
		return skillLevelRepo.findAll();
	}
	

	

}
