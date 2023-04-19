package com.skilldistillery.barter.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="skill_level")
public class SkillLevel {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	int id;
	
	String name;
	
	String description;
	
	@OneToMany(mappedBy="skillLevel")
	private Set<UserSkill> userSkill;
	

	public Set<UserSkill> getUserSkill() {
		return userSkill;
	}

	public void setUserSkill(Set<UserSkill> userSkill) {
		this.userSkill = userSkill;
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
