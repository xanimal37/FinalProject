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

class TaskMessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private TaskMessage taskMessage;
	
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
		taskMessage = em.find(TaskMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		taskMessage=null;
		em.close();
	}

	@Test
	void test_TaskMessage_mapping() {
		assertNotNull(taskMessage);
		assertEquals("Meetup", taskMessage.getTitle());
	}
	
	@Test
	void test_TaskMessage_Task_mapping() {
		assertNotNull(taskMessage);
		assertEquals(1,taskMessage.getTask().getId());
	}
	
	@Test
	void test_TaskMessage_User_mapping() {
		assertNotNull(taskMessage);
		assertEquals("eflatto",taskMessage.getUser().getUsername());
	}
	
	

}
