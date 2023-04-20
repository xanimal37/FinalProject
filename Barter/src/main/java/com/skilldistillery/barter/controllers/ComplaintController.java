package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.Complaint;
import com.skilldistillery.barter.services.ComplaintService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class ComplaintController {

	@Autowired
	private ComplaintService cService;
	
	@GetMapping("complaints")
	public List<Complaint> index(HttpServletRequest req, HttpServletResponse res) {
		return cService.indexAll();
	} 
	
	@GetMapping("complaints/user/{uId}")
	public Set<Complaint> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int uId) {
		return cService.complaintsByUser(uId);
	} 
	
	@GetMapping("complaints/{cId}")
	public Complaint showSingle(HttpServletRequest req, HttpServletResponse res, @PathVariable int cId) {
		Complaint complaint = cService.complaintById(cId);
		if (complaint == null) {
			res.setStatus(404); 
		}
		return complaint;
	}
	
	@PostMapping("complaint")
	public Complaint create(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody Complaint complaint) {
		Complaint newComplaint = null;
		newComplaint = cService.createComplaint(principal, newComplaint)
	}
	
}
