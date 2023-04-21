package com.skilldistillery.barter.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findAll();
	Set<Post> findAllByUser_Id(int userId);
	Post findById(int pId);
	List<Post> findByContentAndComments_ContentLike(String keyword, String Keyword);
	Post findByIdAndUser_Username(int pId, String username);
}
