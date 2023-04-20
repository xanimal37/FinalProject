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
	public List<Notification> indexAll() {
//		User admin = userRepo.findByUsername(username);
//		if (admin.getRole() == "admin") {
		return nRepo.findAll();
//		} else {
//			return null;
//		}
	}
	
	@Override
	public List<NotificationType> findAllTypes() {
		return typeRepo.findAll();
	}

	@Override
	public List<Notification> indexAllByUser(int userId) {
		return nRepo.findAllById(userId);
	}
	
	@Override
	public List<Notification> findAllByTypeId(int typeId) {
		return nRepo.findAllBynType_Id(typeId);
	}

	@Override
	public Notification createNotification(int userId, Notification notification) {
		User user = userRepo.findById(userId);
		if(user != null && notification != null) {
			notification.setUser(user);
			return nRepo.saveAndFlush(notification);
		}
		return null;
	}





}
