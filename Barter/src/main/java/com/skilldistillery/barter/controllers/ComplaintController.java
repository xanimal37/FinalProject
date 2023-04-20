package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("complaints/{uId}")
	public Complaint create( HttpServletRequest req, HttpServletResponse res,@PathVariable int uId, @RequestBody Complaint newComplaint) {
		try {
			newComplaint = cService.createComplaint(uId, newComplaint);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newComplaint;
	}
	
    @PutMapping("complaints/{cId}")
	public Complaint update(HttpServletRequest req, HttpServletResponse res, @PathVariable int cId, @RequestBody Complaint complaint) {
		 try {
			 complaint = cService.updateComplaint(cId, complaint);
			 if (complaint == null) {
				 res.setStatus(404);
			 }else {
				 res.setStatus(200);
			 }
		 } catch ( Exception e){
			 e.printStackTrace();
			 res.setStatus(400);
		 }
		 return complaint;
	}
    
    @PutMapping("complaints/archive/{cId}")
	public void archive(HttpServletRequest req, HttpServletResponse res, @PathVariable int cId) {
		 try {
			if ( cService.archiveComplaint(cId) == false) {
				 res.setStatus(400);
			 }else {
				 res.setStatus(200);
			 }
		 } catch ( Exception e){
			 e.printStackTrace();
			 res.setStatus(400);
		 }
    }
		 
    @DeleteMapping("complaints/{cId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int cId) {
		 try {
			if ( cService.destroyComplaint(cId) == false) {
				 res.setStatus(400);
			 }else {
				 res.setStatus(204);
			 }
		 } catch ( Exception e){
			 e.printStackTrace();
			 res.setStatus(400);
		 }

}
}
