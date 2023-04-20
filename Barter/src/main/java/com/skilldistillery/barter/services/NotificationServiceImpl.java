package com.skilldistillery.barter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.barter.entities.Notification;
import com.skilldistillery.barter.entities.NotificationType;
import com.skilldistillery.barter.entities.User;
import com.skilldistillery.barter.repositories.NotificationRepository;
import com.skilldistillery.barter.repositories.NotificationTypeRepository;
import com.skilldistillery.barter.repositories.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository nRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private NotificationTypeRepository typeRepo;
	
	@Override
	public List<Notification> indexAll(String username) {
		User admin = userRepo.findByUsername(username);
		if (admin.getRole() == "admin") {
		return nRepo.findAll();
		} else {
			return null;
		}
	}
	
	@Override
	public List<NotificationType> findAllTypes(String username) {
		User admin = userRepo.findByUsername(username);
		return typeRepo.findAll();
	}

	@Override
	public List<Notification> indexAllByUser(String username, int userId) {
		User admin = userRepo.findByUsername(username);
		return nRepo.findAllById(null);
	}
	
	@Override
	public List<Notification> findAllByTypeId(String username, int typeId) {
		User admin = userRepo.findByUsername(username);
		return nRepo.findAllBynType_Id(typeId);
	}

	@Override
	public Notification createNotification(String username, int userId, Notification notification) {
		User user = userRepo.findById(userId);
		User admin = userRepo.findByUsername(username);
		if(user != null && notification != null) {
			notification.setUser(user);
			return nRepo.saveAndFlush(notification);
		}
		return null;
	}





}
