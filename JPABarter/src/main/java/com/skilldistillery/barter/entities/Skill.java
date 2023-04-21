package com.skilldistillery.barter.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="task_has_skill",
	joinColumns = @JoinColumn(name="skill_id"),
	inverseJoinColumns = @JoinColumn(name="task_id"))
	private List <Task> tasks;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_skill",
	joinColumns = @JoinColumn(name="skill_id"),
	inverseJoinColumns = @JoinColumn(name="skill_level_id"))
	private List <SkillLevel> skillLevel;
	
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> task) {
		this.tasks = task;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
