package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,@RequestBody User user) {
		
		User updatedUser =null;
		try {
			updatedUser = userService.updateUser(user,userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(updatedUser==null) {
			res.setStatus(400);
		}else if(updatedUser!=null) {
			res.setStatus(200);
		}
		return updatedUser;
	}

//  DELETE users/{userId}
	@DeleteMapping("users/{userId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId) {
		boolean delete = false;
		try {
			delete= userService.deleteUser( userId);
			if(delete) {
				res.setStatus(204);
				
			}else{
				res.setStatus(404);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(500);
		}
		

	}
	

}
