package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

import com.skilldistillery.barter.entities.Comment;
import com.skilldistillery.barter.entities.Post;
import com.skilldistillery.barter.services.PostService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("posts")
	public List<Post> index(HttpServletRequest req, HttpServletResponse res) {
		return postService.indexAll();
	} 
	 
	@GetMapping("posts/user/{uId}")
	public Set<Post> indexByUser( HttpServletRequest req, HttpServletResponse res, @PathVariable int uId) {
		return postService.postsByUser(uId);
	} 
	
	@GetMapping("posts/{pId}")
	public Post indexByPostId( Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int pId) {
		return postService.postById(pId, principal.getName());
	} 
	
	@GetMapping("posts/search/{keyword}")
	public List<Post> postSearch( Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable String keyword) {
		List<Post> posts = postService.postKeywordSearch(principal.getName(), keyword);
		if(posts == null) {
			res.setStatus(401);
		} 
		return posts;
		
	} 
	
	@GetMapping("posts/comments/")
	public List<Comment> indexAllComments(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		return postService.postComments(principal.getName());
	} 
	
	@PostMapping("posts")
	public Post createPost(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody Post post) {
		try {
			post = postService.createPost(principal.getName(), post);
			if (post != null) {
				res.setStatus(201);
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			res.setStatus(400);
		}
		return post;
	} 
	
	@PostMapping("posts/{pId}/comments")
	public Comment createCommentForPost(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int pId, @RequestBody Comment comment) {
		try {
			comment = postService.createComment(principal.getName(), pId, comment);
			if (comment != null) {
				res.setStatus(201);
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			res.setStatus(400);
		}
		return comment;
	} 
	
	
	@PutMapping("posts/{pId}")
	public Post updatePost(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int pId, @RequestBody Post post) {
		try {
			post = postService.updatePost(principal.getName(), pId, post);
			if (post != null) {
				res.setStatus(201);
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return post;
	} 
	
    @PutMapping("posts/disabled/{pId}")
	public Post disablePost(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int pId) {
    	Post disabledPost = null;
    	 try {
    		 disabledPost = postService.disablePost(principal.getName(), pId);
    		 if (disabledPost != null) {
    			 res.setStatus(201);
    			 return disabledPost;
    			 
    		 } else { 
    			 res.setStatus(404);
    			 return null;
    		 }
    		} catch (Exception e) {
    			e.printStackTrace();
    			res.setStatus(400);
    		}
    	 return null;
	}
    
	
	

}
