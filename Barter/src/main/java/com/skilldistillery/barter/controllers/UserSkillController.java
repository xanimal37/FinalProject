package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.Task;
import com.skilldistillery.barter.entities.UserSkill;
import com.skilldistillery.barter.services.TaskService;
import com.skilldistillery.barter.services.UserSkillService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserSkillController {

		@Autowired
		private UserSkillService userSkillService;

		
		@GetMapping(path = "userSkill")
		public List<UserSkill> getAllUserSkills() {
			return userSkillService.getAllUserSkills();
		}

		@PostMapping(path = "userSkill")
		public UserSkill createUserSkill(@RequestBody UserSkill userSkill, Principal principal, HttpServletRequest req,
				HttpServletResponse res) {
			try {
				System.out.println(userSkill);
				userSkill = userSkillService.createNewUserSkill(userSkill, principal.getName());
				res.setStatus(201);

			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(404);
				userSkill = null;
			}
			return userSkill;
		}


}
