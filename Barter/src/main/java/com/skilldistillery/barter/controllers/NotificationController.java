package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

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

import com.skilldistillery.barter.entities.Notification;
import com.skilldistillery.barter.entities.NotificationType;
import com.skilldistillery.barter.services.NotificationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class NotificationController {
	@Autowired
	private NotificationService nService;
	
	@GetMapping("notifications")
	public List<Notification> index(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		List<Notification> notes = null;
		try {
			nService.indexAll(principal.getName());
			if (notes != null) {
			res.setStatus(200);
			return notes; 
			}
		} catch (Exception e) {

			e.printStackTrace();
			res.setStatus(400);
		}
		return nService.indexAll(principal.getName());
		
	} 
	
	@GetMapping("notifications/user/{uId}")
	public List<Notification> indexByUser(Principal principal,HttpServletRequest req, HttpServletResponse res, @PathVariable int uId) {
		return nService.indexAllByUser(principal.getName(), uId);
	} 
	
	@GetMapping("notifications/type/{tId}")
	public List<Notification> indexByType(Principal principal,HttpServletRequest req, HttpServletResponse res, @PathVariable int tId) {
		return nService.findAllByTypeId(principal.getName(),tId);
	} 
	
	@GetMapping("notifications/type/")
	public List<NotificationType> indexAllTypes(Principal principal,HttpServletRequest req, HttpServletResponse res, @PathVariable int tId) {
		return nService.findAllTypes(principal.getName());
	} 
	
	@PostMapping("notifications/{uId}")
	public Notification createNotification(Principal principal,HttpServletRequest req, HttpServletResponse res, @RequestBody Notification notification, @PathVariable int uId) {
		Notification newNotification = null;
		try {
			newNotification = nService.createNotification(principal.getName(), uId, notification);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newNotification;

	}
	
}
