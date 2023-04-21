package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByPost_Id(int pId);

}
