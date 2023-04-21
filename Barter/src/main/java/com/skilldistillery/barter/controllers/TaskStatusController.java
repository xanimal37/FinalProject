package com.skilldistillery.barter.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.barter.entities.TaskStatus;
import com.skilldistillery.barter.services.TaskStatusService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class TaskStatusController {

	@Autowired
	private TaskStatusService taskStatusService;

	@GetMapping(path = "taskStatuses")
	List<TaskStatus> getAllTaskStatuses() {
		return taskStatusService.getAllTaskStatuses();
	}

	@GetMapping(path = "taskStatuses/{id}")
	TaskStatus getTaskStatus(@PathVariable int id) {
		return taskStatusService.getTaskStatusById(id);
	}

	// require user to be admin
	@PostMapping(path = "taskStatuses")
	TaskStatus createTaskStatus(@RequestBody TaskStatus taskStatus, Principal principal, HttpServletRequest req,
			HttpServletResponse res) {

		try {
			taskStatus = taskStatusService.createTaskStatus(taskStatus, principal.getName());
			res.setStatus(201);
			// StringBuffer url = req.getRequestURL(); // define as stringbuffer
			// url.append("/").append(task.getId()); // append id to url so will show user
			// the post url
			// res.setHeader("Location", "http://localhost:8083"); // location
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			taskStatus = null;
		}
		return taskStatus;
	}

	@PutMapping(path = "taskStatuses/{id}")
	TaskStatus updateTaskStatus(@RequestBody TaskStatus taskStatus, @PathVariable int id, Principal principal,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			taskStatus = taskStatusService.updateTaskStatus(taskStatus, id, principal.getName());
			if (taskStatus == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			taskStatus = null;
		}
		return taskStatus;
	}

	@DeleteMapping(path="taskStatuses/{id}")
	public void deleteTaskStatus(@PathVariable int id,Principal principal ,HttpServletRequest req,HttpServletResponse res ) {
	try {
		if (taskStatusService.deleteTaskStatus(id,principal.getName())) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(400);
	}
	
	}
}
