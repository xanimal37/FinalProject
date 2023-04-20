package com.skilldistillery.barter.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.barter.entities.Complaint;


public interface ComplaintService {
	
    public List<Complaint> indexAll();

    public Set<Complaint> complaintsByUser(int userId);
    
    Complaint complaintById(int cId); 

    public Complaint createComplaint(int uId, Complaint complaint);

    public Complaint updateComplaint(int cId, Complaint complaint);

    public boolean destroyComplaint(int cId);
    
    public boolean archiveComplaint(int cId);

}
