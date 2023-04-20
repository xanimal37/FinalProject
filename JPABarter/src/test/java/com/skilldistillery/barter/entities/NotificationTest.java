package com.skilldistillery.barter.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Notification notification;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPABarter");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		notification = em.find(Notification.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		notification = null;
	}

	@Test
	void test_Notification_Entity_Mapping() {
		assertNotNull(notification);
		assertEquals("You have a new update to your account", notification.getMessage());
	}
	
	@Test
	void test_Notification_NType_Entity_Mapping() {
		assertNotNull(notification.getnType());
		assertEquals(4, notification.getnType().getId());
	}
	
	@Test
	void test_Notification_User_Entity_Mapping() {
		assertNotNull(notification.getUser());
		assertEquals("Zeeb",notification.getUser().getLastname());
	}

}
