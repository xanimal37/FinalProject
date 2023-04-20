package com.skilldistillery.barter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.barter.entities.NotificationType;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer>{

	
}
