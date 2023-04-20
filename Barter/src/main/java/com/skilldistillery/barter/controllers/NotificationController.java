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
	public List<Notification> index(HttpServletRequest req, HttpServletResponse res) {
		return nService.indexAll();
		
	} 
	
	@GetMapping("notifications/user/{uId}")
	public List<Notification> indexByUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int uId) {
		return nService.indexAllByUser( uId);
	} 
	
	@GetMapping("notifications/type/{tId}")
	public List<Notification> indexByType(HttpServletRequest req, HttpServletResponse res, @PathVariable int tId) {
		return nService.findAllByTypeId(tId);
	} 
	
	@GetMapping("notifications/type/")
	public List<NotificationType> indexAllTypes(HttpServletRequest req, HttpServletResponse res, @PathVariable int tId) {
		return nService.findAllTypes();
	} 
	
	@PostMapping("notifications/{uId}")
	public Notification createNotification(HttpServletRequest req, HttpServletResponse res, @RequestBody Notification notification, @PathVariable int uId) {
		Notification newNotification = null;
		try {
			newNotification = nService.createNotification( uId, notification);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newNotification;

	}
	
}
