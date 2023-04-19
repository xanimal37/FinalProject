package com.skilldistillery.barter.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comm;
	

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
		comm = em.find(Comment.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comm = null;
	}

	@Test
	void test_Comm_Entity_Mapping() {
		assertNotNull(comm);
		assertEquals("Make sure your pipe fitting matched and you used threading tape", comm.getContent());
	}
	
	@Test
	void test_Comm_Post() {
		assertNotNull(comm.getPost());
		assertEquals(1, comm.getPost().getId());
	}
	
	@Test
	void test_Comm_Comm_mapping() {
		assertNotNull(comm.getComments());
		assertFalse(comm.getComments() == null);
	}

	//test once updated user complete
//	@Test
//	void test_Comm_User_mapping() {
//		assertNotNull(comm.getUser());
//		assertEquals(3, comm.getUser().getId());
//	}
}
