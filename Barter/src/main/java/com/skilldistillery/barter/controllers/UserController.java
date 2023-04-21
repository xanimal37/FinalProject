package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {
	@Autowired
	private UserService userService;
	

//  GET users
	@GetMapping("users")
	public List<User> index( HttpServletRequest req, HttpServletResponse res) {
		 List<User> users = userService.getAllUsers();
		 if(users==null) {
			 res.setStatus(404);
		 }
		return users;
		
	}
	

//  GET users/{userId}
	@GetMapping("users/{userId}")
	public User showById( HttpServletRequest req, HttpServletResponse res, @PathVariable int userId) {
		
		User user = userService.findById(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return userService.findById(userId);
	}


//  PUT users/{userId}
	@PutMapping("users")
	public User update(HttpServletRequest req, HttpServletResponse res, Principal principal,@RequestBody User user) {

	    User updatedUser = null;
	    try {
	        updatedUser = userService.updateAccount(user,principal.getName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if (updatedUser == null) {
	        res.setStatus(400);
	    } else {
	        res.setStatus(200);
	    }
	    return updatedUser;
	}
	
	@PutMapping("users/{userId}")
	public User adminUpdate(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int userId,@RequestBody User user) {
	    String username = principal.getName(); 
	    User currentUser = userService.findByUsername(username);
//	    must be admin or current user to update
	    if (currentUser.getId() == userId || !currentUser.getRole().equals("admin")) { 
	        res.setStatus(401);
	        return null;
	    }
		User updatedUser = null;
		try {
			updatedUser = userService.updateUserByAdmin(user,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (updatedUser == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
		}
		return updatedUser;
	}
	
	@PostMapping("users/friends/{friendId}")
	public String addFriend(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int friendId) {
	    String username = principal.getName(); 
	    User user = userService.findByUsername(username); 
	    User friend = userService.findById(friendId); 
	    String message = userService.addFriend(user, friend);
	    return message;
	}
	 @GetMapping("users/skills/{skillName}")
	    public List<User> getUsersBySkill(@PathVariable String skillName) {
	        return userService.getUsersBySkill(skillName);
	  }
	 
	 @GetMapping("users/count")
	    public long getUserCount() {
	        return userService.getUserCount();
	    }
	 
	 @GetMapping("users/skillLevel/{skillLevel}")
	 public List<User> getUsersBySkillLevel(@PathVariable String skillLevel) {
		 return userService.getUsersBySkillLevel(skillLevel);
	 }
	 @GetMapping("users/skillName/{skillName}/skillLevel/{skillLevel}")
	 public List<User> getUsersBySkillLevelAndSkillName(@PathVariable String skillLevel,@PathVariable String skillName) {
		 return userService.getUsersBySkillLevelAndSkillName(skillLevel,skillName);
	 }
	 @GetMapping("users/ranking/{rankName}")
	 public List<User> getUsersByRanking(@PathVariable String rankName) {
		 return userService.getUsersByRanking(rankName);
	 }
	
	 
	 


}
