package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.barter.entities.Comment;
import com.skilldistillery.barter.entities.Post;

public interface PostService {
	
    public List<Post> indexAll();

    public Set<Post> postsByUser(int userId);
    
    Post postById(int pId, String username); 

    public Post createPost(String username, Post post);

    public Post updatePost(String username, int pId, Post post);

    Post disablePost(String username, int pId, Post post);
    
    List<Post> postKeywordSearch(String username, String keyword);
    
    List<Comment> postComments(String username);
    
    Comment createComment(String username, int pId, Comment comment);
    
    Comment updateComment(String username, int cId, Comment comment) ;


}
