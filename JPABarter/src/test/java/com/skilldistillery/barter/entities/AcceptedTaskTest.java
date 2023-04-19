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

class AcceptedTaskTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private AcceptedTask acceptedTask;

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
		AcceptedTaskId atId = new AcceptedTaskId();
		atId.setAcceptorId(2);
		atId.setTaskId(2);
		acceptedTask = em.find(AcceptedTask.class, atId);
	}

	@AfterEach
	void tearDown() throws Exception {
		acceptedTask=null;
		em.close();
	}

	@Test
	void test_AcceptedTask_mapping() {
		assertNotNull(acceptedTask);
		assertEquals(5,acceptedTask.getRatingByRequestor());
		
	
	}

}
