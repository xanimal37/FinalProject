package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(path="skills/{name}")
	public List<Skill> getSkillsBySkillLevel(@PathVariable String name){
		return skillService.getSkillBySkillLevelName( name);
	}
	
	@GetMapping(path="skills")
	public List<Skill> getUsersSkills(Principal principal){
		return skillService.getSkillByUser(principal.getName());
	}
	
}
