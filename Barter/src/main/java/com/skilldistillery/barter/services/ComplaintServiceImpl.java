package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Complaint;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.ComplaintRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class ComplaintServiceImpl implements ComplaintService{
	
	@Autowired
	private ComplaintRepository cRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Complaint> indexAll() {
		return cRepo.findAll();
	}

	@Override
	public Set<Complaint> complaintsByUser(int userId) {
		return cRepo.findByUser_id(userId);
	}
	@Override
	public Complaint complaintById(int cId) {
		return cRepo.findById(cId);
	}

	@Override
	public Complaint createComplaint(String username, Complaint complaint) {
		User user = userRepo.findByUsername(username);
		if (complaint != null) {
			complaint.setUser(user);
			return cRepo.saveAndFlush(complaint);
		}
		return null;
	}

	@Override
	public Complaint updateComplaint(String username, int cId, Complaint complaint) {
		Complaint updateComplaint = cRepo.findById(cId);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			updateComplaint.setId(cId);
			updateComplaint.setName(complaint.getName());
			updateComplaint.setDescription(complaint.getDescription());
			updateComplaint.setClosedDate(complaint.getClosedDate());
			return cRepo.saveAndFlush(updateComplaint);
		}
		return null;
	}

	@Override
	public boolean destroyComplaint(String username, int cId) {
		boolean removed = true;
		Complaint deleteComplaint = cRepo.findById(cId);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			cRepo.delete(deleteComplaint);
			if (cRepo.findById(cId) == null) {
				return removed;
			}
		}
		return false;
	}

	@Override
	public boolean archiveComplaint(String username, int cId) {
		boolean archived = false;
		boolean active = false;
		Complaint archiveComplaint = cRepo.findById(cId);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			archiveComplaint.setActive(active);
			cRepo.saveAndFlush(archiveComplaint);
			archived = true;
			return archived;
		}
		
		return archived;
	}

}
