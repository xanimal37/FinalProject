package com.skilldistillery.barter.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.barter.entities.Complaint;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.ComplaintRepository;
import com.skilldistillery.barter.repositories.UserRepository;

public abstract class ComplaintServiceImpl implements ComplaintService{
	
	@Autowired
	private ComplaintRepository cRepo;
	
	private UserRepository userRepo;

	@Override
	public List<Complaint> indexAll() {
		return cRepo.findAll();
	}

	@Override
	public Set<Complaint> complaintsByUser(int userId) {
		return cRepo.findByUser_id(userId);
	}
	
	public Complaint complaintById(int cId) {
		return cRepo.findById(cId);
	}

	@Override
	public Complaint createComplaint(String username, Complaint complaint) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			complaint.setUser(user);
			return cRepo.saveAndFlush(complaint);
		}
		return null;
	}

	@Override
	public Complaint updateComplaint(String username, int cId, Complaint complaint) {
		User user = userRepo.findByUsername(username);
		Complaint updateComplaint = cRepo.findById(cId);
		if (user != null && updateComplaint != null) {
			updateComplaint.setId(cId);
			updateComplaint.setName(complaint.getName());
			updateComplaint.setDescription(complaint.getDescription());
			updateComplaint.setClosedDate(complaint.getClosedDate());
			return cRepo.saveAndFlush(updateComplaint);
		}
		return null;
	}

	@Override
	public boolean destroy(int cId) {
		boolean removed = true;
		Complaint deleteComplaint = cRepo.findById(cId);
		if (deleteComplaint != null) {
			cRepo.delete(deleteComplaint);
			if (cRepo.findById(cId) == null) {
				return removed;
			}
		}
		return false;
	}

	@Override
	public boolean archive(int cId) {
		boolean archived = false;
		boolean active = false;
		Complaint archiveComplaint = cRepo.findById(cId);
		if (archiveComplaint != null) {
			archiveComplaint.setActive(active);
			cRepo.saveAndFlush(archiveComplaint);
			archived = true;
			return archived;
		}
		
		return archived;
	}

}
