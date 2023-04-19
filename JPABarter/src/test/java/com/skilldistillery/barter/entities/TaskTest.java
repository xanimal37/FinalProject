package com.skilldistillery.barter.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Task task;

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
		task = em.find(Task.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		task=null;
		em.close();
	}

	@Test
	void test_Task_mapping() {
		assertNotNull(task);
		assertEquals("Bathroom Sink",task.getName());
	}
	
	@Test
	void test_Task_TaskStatus_mapping() {
		assertNotNull(task);
		assertEquals("Pending",task.getTaskStatus().getName());
	}
	
	@Test 
	void test_Task_TaskMessage_mapping() {
		assertNotNull(task);
		assertEquals(1,task.getTaskMessages().size());
	}
	
	@Test
	void test_Task_User_mapping() {
		assertNotNull(task);
		assertEquals("eflatto",task.getUser().getUsername());
	}
	
	@Test
	void test_Task_Address_mapping() {
		assertNotNull(task);
		assertEquals(1,task.getAddress().getId());
	}
	
	@Test
	void test_Task_Skill_mapping() {
		assertNotNull(task);
		assertEquals(3,task.getSkills().size());
	}

}
