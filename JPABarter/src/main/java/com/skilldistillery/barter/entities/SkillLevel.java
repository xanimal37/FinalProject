package com.skilldistillery.barter.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="skill_level")
public class SkillLevel {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	int id;
	
	String name;
	
	String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="skillLevel")
	private Set<UserSkill> userSkills;
	

	public Set<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(Set<UserSkill> userSkill) {
		this.userSkills = userSkill;
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
