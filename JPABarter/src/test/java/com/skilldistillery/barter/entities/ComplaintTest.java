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

class ComplaintTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Complaint complaint;
	

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
		complaint = em.find(Complaint.class, 1);
	}
	
 
	@AfterEach
	void tearDown() throws Exception {
		em.close();
		complaint = null;
	}

	@Test
	void test_Complaint_Entity_Mapping() {
		assertNotNull(complaint);
		assertEquals("Foul Behavior ", complaint.getName());
	}
	
	@Test
	void test_Complaint_User_Entity_Mapping() {
		assertNotNull(complaint.getUser());
		assertEquals(2, complaint.getUser().getId());
	}
}
