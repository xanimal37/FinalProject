package com.skilldistillery.barter.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="user_skill")
@Entity

public class UserSkill {
	
	@EmbeddedId
	private UserSkillID id;
	
	private String certification;
	
	private String description;
	
	@JsonIgnoreProperties({"userSkills"})
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@JsonIgnoreProperties({"userSkills"})
	@ManyToOne
	@JoinColumn(name="skill_id")
	@MapsId(value="skillId")
	private Skill skill;

	@JsonIgnoreProperties({"userSkills"})
	@ManyToOne
	@JoinColumn(name= "skill_level_id")
	private SkillLevel skillLevel;
	
	
	public Skill getSkill() {
		return skill;
	}


	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	
	public SkillLevel getSkillLevel() {
		return skillLevel;
	}


	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public UserSkillID getId() {
		return id;
	}


	public void setId(UserSkillID id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getCertification() {
		return certification;
	}


	public void setCertification(String certification) {
		this.certification = certification;
	}


}
