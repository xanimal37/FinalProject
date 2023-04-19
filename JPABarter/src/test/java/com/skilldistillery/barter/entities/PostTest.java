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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;
	

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
		post = em.find(Post.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test
	void test_Post_Entity_Mapping() {
		assertNotNull(post);
		assertEquals("Plumbing Question", post.getTitle());
	}
	
	@Test
	void test_Post_User_Mapping() {
		assertNotNull(post.getUser());
		assertEquals(2, post.getUser().getId());
	}
	

	
	@Test
	void test_Post_Coment() {
		assertNotNull(post.getComments());
		assertFalse(post.getComments() == null);
	}
}
