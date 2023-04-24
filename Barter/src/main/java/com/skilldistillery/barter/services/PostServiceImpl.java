package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Comment;
import com.skilldistillery.barter.entities.Post;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.CommentRepository;
import com.skilldistillery.barter.repositories.PostRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Post> indexAll() {
		return postRepo.findAll();
	}

	@Override
	public Set<Post> postsByUser(int userId) {
		return postRepo.findAllByUser_Id(userId);
	}

	@Override
	public Post postById(int pId, String username) {
		return postRepo.findByIdAndUser_Username(pId, username);
	}

	@Override
	public Post createPost(String username, Post post) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			post.setUser(user);
			postRepo.saveAndFlush(post);
			return post;
		}
		return null;
	}

	@Override
	public Comment createComment(String username, int pId, Comment comment) {
		User user = userRepo.findByUsername(username);
		Post post = postRepo.findById(pId);
		if (user != null) {
			if (post != null) {
			comment.setUser(user);
			comment.setPost(post);
			commentRepo.saveAndFlush(comment);
			return comment;
		}
		}
		return comment;
	}

	@Override
	public Post updatePost(String username, int pId, Post post) {
		User user = userRepo.findByUsername(username);
		Post updatePost = postRepo.findById(pId);
		if (user != null) {
			updatePost.setUser(user);
			updatePost.setTitle(post.getTitle());
			updatePost.setContent(post.getContent());
			return postRepo.saveAndFlush(updatePost);
		}
		return null;
	}

	@Override
	public Comment updateComment(String username, int cId, Comment comment) {
		User user = userRepo.findByUsername(username);
		Comment updatePost = commentRepo.findById(cId);
		if (user != null) {
			updatePost.setUser(user);
			updatePost.setContent(comment.getContent());
			return commentRepo.saveAndFlush(updatePost);
		}
		return null;
	}

	@Override
	public Post disablePost(String username, int pId) { 
		User user = userRepo.findByUsername(username);
		Post disablePost = postRepo.findById(pId);
		if (user != null) {
			disablePost.setEnabled(false);
			
			if (disablePost.isEnabled() == false) {
				return disablePost;
			}
		}
		return null;
	}

	@Override
	public List<Post> postKeywordSearch(String username, String keyword) {
		System.out.println(keyword);
		String newKeyword = "%" + keyword + "%";
		System.out.println(newKeyword);
		User user = userRepo.findByUsername(username);
		if (user != null && (user.getRole().equals("user" )|| user.getRole().equals("admin"))) {
			return postRepo.findByContentLikeOrComments_ContentLike(newKeyword, newKeyword);
		} else {
			return null;
		}
	}

	public List<Comment> postComments(String username, int pId) {
		User user = userRepo.findByUsername(username);
		if (user.getRole() == "user" || user.getRole() == "admin") {
			return commentRepo.findByPost_Id(pId);
		} else {
			return null;
		}
	}

}
