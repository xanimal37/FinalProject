package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.barter.entities.Complaint;


public interface ComplaintService {
	
    public List<Complaint> indexAll();

    public Set<Complaint> complaintsByUser(int userId);
    
    Complaint complaintById(int cId); 

    public Complaint createComplaint(String username, Complaint complaint);

    public Complaint updateComplaint(String username, int cId, Complaint complaint);

    public boolean destroyComplaint(String username, int cId);
    
    public boolean archiveComplaint(String username, int cId);

}
