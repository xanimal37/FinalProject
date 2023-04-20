//package com.skilldistillery.barter.controllers;
//
//import java.security.Principal;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.skilldistillery.barter.entities.User;
//import com.skilldistillery.barter.services.UserService;
//
//@RestController
//@RequestMapping("api")
//@CrossOrigin({ "*", "http://localhost/" })
//public class UserController {
//	@Autowired
//	private UserService userService;
//
////  GET users
//	@GetMapping("users")
//	public Set<User> index(Principal principal, HttpServletRequest req, HttpServletResponse res) {
//		return userService.getAllUsers();
//		
//	}
////	@GetMapping("posts/{postId}/comments")
////	public List<Comment> listCommentsForPost(@PathVariable Integer postId) {
////		List<Comment> comments = commentService.getCommentsForPost(postId);
////		return comments;
////	}
//	
//
////  GET users/{tid}
//	@GetMapping("users/{tid}")
//	public User show(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int tid) {
//		
//		User todo = userService.show(principal.getName(), tid);
//		if (todo == null) {
//			res.setStatus(404);
//		}
//		return userService.show(principal.getName(), tid);
//	}
//
////  POST users
//	@PostMapping("users")
//	public User create(HttpServletRequest req, HttpServletResponse res,@RequestBody User todo,Principal principal) {
//		User newUser = userService.create(principal.getName(), todo);
//		if(newUser==null) {
//			res.setStatus(400);
//		}else {
//			res.setStatus(201);
//		}
//		return newUser;
//	}
//
////  PUT users/{tid}
//	@PutMapping("users/{tid}")
//	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, @RequestBody User todo,Principal principal) {
//		
//		User updatedUser =null;
//		try {
//			updatedUser = userService.update(principal.getName(), tid, todo);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(updatedUser==null) {
//			res.setStatus(400);
//		}else if(updatedUser!=null) {
//			res.setStatus(200);
//		}
//		return updatedUser;
//	}
//
////  DELETE users/{tid}
//	@DeleteMapping("users/{tid}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid,Principal principal) {
//		boolean delete = false;
//		try {
//			delete= userService.destroy(principal.getName(), tid);
//			if(delete) {
//				res.setStatus(204);
//				
//			}else{
//				res.setStatus(404);
//				
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			res.setStatus(500);
//		}
//		
//
//	}
//	

//}
