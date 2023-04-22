package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.Skill;
import com.skilldistillery.barter.services.SkillService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@GetMapping(path="skills/skillslevel/{name}")
	public List<Skill> getSkillsBySkillLevel(@PathVariable String name){
		return skillService.getSkillBySkillLevelName( name);
	}
	
	@GetMapping(path="skills/user")
	public List<Skill> getUsersSkills(Principal principal){
		return skillService.getSkillByUser(principal.getName());
	}
	
	@GetMapping("skills")
	public List<Skill> getAllSkills() {
		return skillService.getAllSkills();
	}
	@PutMapping("skills/{skillId}")
	public Skill updateSkill(@PathVariable int skillId, @RequestBody Skill skillDetails) {
		return skillService.updateSkill(skillId, skillDetails);
	}
	@DeleteMapping("skills/{skillId}")
	public void deleteSkill(@PathVariable int skillId) {
		skillService.deleteSkill(skillId);
	}
	@GetMapping("skills/task/{task}")
	public List<Skill> getSkillByTaskName(@PathVariable String task) {
		return skillService.getSkillByTaskName(task);
	}
	@GetMapping("skill/user/{username}")
	public List<Skill> getSkillByUser(@PathVariable String username) {
		return skillService.getSkillByUser(username);
	}
	@PostMapping("skills")
	public Skill createSkill(@RequestBody Skill skill) {
		return skillService.createSkill(skill);
	}
	
}
