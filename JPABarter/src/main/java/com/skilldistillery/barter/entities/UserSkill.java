package com.skilldistillery.barter.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.ws.soap.MTOM;

@Table(name="user_skill")
@Entity
public class UserSkill {
	
	@EmbeddedId
	private UserSkillID id;
	
	private String certification;
	
	private String description;
	

	@ManyToOne
	@JoinColumn(name= "skill_level_id")
	private SkillLevel skillLevel;
	
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


	public String getCertification() {
		return certification;
	}


	public void setCertification(String certification) {
		this.certification = certification;
	}


}
