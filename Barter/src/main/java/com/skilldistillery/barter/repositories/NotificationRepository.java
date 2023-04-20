package com.skilldistillery.barter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	List<Notification> findAllById(int userId);
	List<Notification> findAllBynType_Id(int typeId);
	

}
