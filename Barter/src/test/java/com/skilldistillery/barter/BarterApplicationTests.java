//package com.skilldistillery.barter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//
//
//	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//	class JobTrackerApplicationTests {
//		private static String url = "/api/jobapplications";
//		
//		
//		@Autowired
//		private TestRestTemplate restTest;
//		
//		@Test
//		public void test_jobapp_list() {
//			@SuppressWarnings("rawtypes")
//			ResponseEntity<List> entity = restTest.getForEntity(url, List.class);
//			assertEquals(HttpStatus.OK, entity.getStatusCode());
//			@SuppressWarnings("unchecked")
//			List<JobApplication> jobApp = (List<JobApplication>)entity.getBody();
//			assertTrue(jobApp.size()>0);
//		}
//		
//		@Test
//		public void test_jobapp_retrieve() {
//			int jobId = 1;
//			ResponseEntity<JobApplication> entity = restTest.getForEntity(url + "/" + jobId, JobApplication.class);
//			assertEquals(HttpStatus.OK, entity.getStatusCode());
//			JobApplication jobApp = (JobApplication)entity.getBody();
//			assertNotNull(jobApp);
//			assertEquals("Google", jobApp.getCompanyName());
//		}
//		
//		@Test
//		public void test_jobapp_retrieve_returns_404_for_invalid_id() {
//			int jobId = 999999;
//			ResponseEntity<JobApplication> entity = restTest.getForEntity(url + "/" + jobId, JobApplication.class);
//			assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
//		}
//		
//		@Test
//		public void test_job_create() {
//			JobApplication newJob = new JobApplication();
//			newJob.setCompanyName("NEW TEST APP " + Math.random()); // title is unique
//			Status status = new Status();
//			status.setId(1);
//	        	newJob.setStatus(status);
//			ResponseEntity<JobApplication> entity = restTest.postForEntity(url, newJob, JobApplication.class);
//			assertEquals(HttpStatus.CREATED, entity.getStatusCode());
//			JobApplication jobApp = (JobApplication)entity.getBody();
//			assertNotNull(jobApp);
//		}
//		
//		@Test
//		public void test_job_delete() {
//			JobApplication newJobApp = new JobApplication();
//			newJobApp.setCompanyName("NEW APP TO DELETE");
//			Status status = new Status();
//			status.setId(1);
//			newJobApp.setStatus(status);
//			ResponseEntity<JobApplication> entity = restTest.postForEntity(url, newJobApp, JobApplication.class);
//			assertEquals(HttpStatus.CREATED, entity.getStatusCode());
//			JobApplication job = (JobApplication)entity.getBody();
//			assertNotNull(job);
//			int idToDelete = job.getId();
//			restTest.delete(url + "/" + idToDelete);
//			assertEquals(HttpStatus.NOT_FOUND,restTest.getForEntity(url + "/" + idToDelete, JobApplication.class).getStatusCode());
//		}
//
//	}
//
//
//}
