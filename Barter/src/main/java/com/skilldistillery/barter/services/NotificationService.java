package com.skilldistillery.barter.services;

import java.util.List;

import com.skilldistillery.barter.entities.Notification;
import com.skilldistillery.barter.entities.NotificationType;

public interface NotificationService {
	
	List<Notification> indexAllByUser(int userId);
	List<Notification> indexAll();
	List<Notification> findAllByTypeId(int typeId);
	List<NotificationType> findAllTypes();
	Notification createNotification( int userId, Notification notification);

}
