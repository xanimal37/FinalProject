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

class TaskStatusTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TaskStatus taskStatus;

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
		taskStatus = em.find(TaskStatus.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		taskStatus=null;
		em.close();
	}

	@Test
	void test_TaskStatus_mapping() {
		assertNotNull(taskStatus);
		assertEquals("Pending",taskStatus.getName());
	}

}
