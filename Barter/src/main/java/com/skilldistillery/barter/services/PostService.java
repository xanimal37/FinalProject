package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;


import com.skilldistillery.barter.entities.Post;

public interface PostService {
	
    public List<Post> indexAll();

    public Set<Post> postsByUser(int userId);
    
    Post postById(int pId); 

    public Post createPost(String username, Post post);

    public Post updatePost(String username, int pId, Post post);

    public boolean destroyPost(String username, int pId);
    
    List<Post> postKeywordSearch(String username, String keyword);
    


}
