package com.skilldistillery.barter.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer>  {
	
	Set<Complaint> findByUser_id(int userId);
	List<Complaint> findAll();
	Complaint findById(int cId);

}
