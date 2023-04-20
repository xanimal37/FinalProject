package com.skilldistillery.barter.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	private Comment comm2;
	

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
		comm2 = em.find(Comment.class, 2);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comm = null;
		comm2 = null;
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
		assertTrue(comm.getComments().size() > 0);
	}
	
	@Test
	void test_Comm_Comm_Reply_mapping() {
		assertEquals("Make sure your pipe fitting matched and you used threading tape",comm2.getInReplyTo().getContent());
	}

	@Test
	void test_Comm_User_mapping() {
		assertNotNull(comm.getUser());
		assertEquals("Lisa", comm.getUser().getFirstname());
	}
}
