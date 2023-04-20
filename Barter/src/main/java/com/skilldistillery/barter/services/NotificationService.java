package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Notification;
import com.skilldistillery.barter.entities.NotificationType;

public interface NotificationService {
	
	List<Notification> indexAllByUser(String username, int userId);
	List<Notification> indexAll(String username);
	List<Notification> findAllByTypeId(String username,int typeId);
	List<NotificationType> findAllTypes(String username);
	Notification createNotification(String username, int userId, Notification notification);

}
