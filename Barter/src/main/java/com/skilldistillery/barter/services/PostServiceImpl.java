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
public class PostServiceImpl implements PostService{
	
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
		}
		return null;
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
	public boolean destroyPost(String username, int pId) {
		boolean removed = true;
		User user = userRepo.findByUsername(username);
		Post deletePost = postRepo.findById(pId);
		if(user != null) {
			postRepo.delete(deletePost);
			if(postRepo.findById(pId) == null)
				return removed;
		}
		return false;
	}
	
	@Override
	public List<Post> postKeywordSearch(String username, String keyword) {
		String newKeyword = "%" + keyword + "%";
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return postRepo.findByContentAndComments_ContentLike(newKeyword, newKeyword);
		} else {
		return null;
		}
	}
	
	public List<Comment> postComments(String username, int pId) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return commentRepo.findByPost_Id(pId);
		} else {
			return null;
		}
	}
}
