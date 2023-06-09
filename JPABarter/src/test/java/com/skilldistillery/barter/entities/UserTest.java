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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	private User userTwo;
	private User userThree;
	

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
		user = em.find(User.class, 1);
		userTwo = em.find(User.class, 2);
		userThree = em.find(User.class, 3);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_Entity_Mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS", user.getPassword());
		assertEquals(null, user.getAvailability());
		assertEquals(null, user.getBiography());
		assertEquals(2013, user.getCreatedDate().getYear());
		assertEquals(null, user.getUpdatedDate());
		assertEquals(null, user.getEmail());
		assertEquals("George", user.getFirstname());
		assertEquals("Washington", user.getLastname());
		assertEquals(null, user.getPhone());
		assertEquals("admin", user.getRole());
		assertEquals(true, user.isEnabled());
		
	}
	
	@Test
	void test_User_Ranking_Mapping() {
		assertNotNull(user);
		assertEquals("Poor", user.getRanking().getName());
		
	}
	
	@Test
	void test_User_has_friends() {
		assertNotNull(user);
		assertEquals(true, userTwo.getFriends().size()>0);
		assertEquals(false, user.getFriends().size()>0);
		
	}
	@Test
	void test_User_Address_Mapping() {
		assertNotNull(user);
		assertEquals("Denver", user.getAddress().getCity());
		
	}
	@Test
	void test_User_Task_Mapping() {
		assertEquals("Bathroom Sink", userTwo.getTasks().get(0).getName());
		
	}
	
	@Test
	void test_User_Comment_Mapping() {
		assertNotNull(user);
		assertEquals("Make sure your pipe fitting matched and you used threading tape", userThree.getComments().get(0).getContent());
		
	}
	@Test
	void test_User_TaskMessage_Mapping() {
		assertNotNull(user);
		assertEquals("Lets sync at xyz", userTwo.getTaskMessages().get(0).getContent());
		
	}
	@Test
	void test_User_Complaint_Mapping() {
		assertNotNull(user);
		assertEquals("Zeeb was supposed to trim my tree but instead stole my miniature Donald Trump commemorative dinner plates", userTwo.getComplaints().get(0).getDescription());
		
	}
	@Test
	void test_User_Notification_Mapping() {
		assertNotNull(user);
		assertEquals("You have a new update to your account", userThree.getNotifications().get(0).getMessage());
		
	}
	@Test
	void test_User_Post_Mapping() {
		assertNotNull(user);
		assertEquals("Whats the best way to install an L joint with a flex host on an existing sprinkler system", userTwo.getPosts().get(0).getContent());
		
	}
//	
//	@Test
//	void test_User_User_Skill_Mapping() {
//		assertNotNull(user);
//		assertEquals("Apprentice Plumber with union", userTwo.getUserSkill().get(0).getDescription());
//		
//	}
//	@Test
//	void test_User_Skill_Mapping() {
//		assertNotNull(user);
//		assertEquals("Plumbing", userTwo.getUserSkills().get(0).getName());
//		
//	}
	
//	@Test
//	void test_User_SkillLevel_Mapping() {
//		assertNotNull(user);
//		assertEquals("Plumbing", userTwo.getSkil);
//		
//	}
	
	

}
