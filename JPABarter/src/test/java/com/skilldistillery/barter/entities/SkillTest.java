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

public class SkillTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Skill skill;
	
	

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
		skill = em.find(Skill.class, 1);
	
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		skill = null;
	}

	@Test
	void test_Skill_Entity_Mapping() {
		assertNotNull(skill);
		assertEquals("Carpentry", skill.getName());
		assertEquals(null, skill.getDescription());
	}
	@Test
	void test_Skill_Task_Mapping() {
		assertNotNull(skill);
		assertEquals("Bathroom Sink", skill.getTasks().get(0).getName());
	}
}
