package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.barter.entities.Complaint;
import com.skilldistillery.barter.entities.User;

public interface ComplaintService {
	
    public List<Complaint> indexAll();

    public Set<Complaint> complaintsByUser(int userId);
    
    Complaint complaintById(int cId); 

    public Complaint createComplaint(String username, Complaint complaint);

    public Complaint updateComplaint(String username, int cId, Complaint complaint);

    public boolean destroy(int cId);
    
    public boolean archive(int cId);

}
