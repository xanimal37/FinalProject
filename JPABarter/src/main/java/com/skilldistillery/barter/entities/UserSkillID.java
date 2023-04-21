package com.skilldistillery.barter.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserSkillID implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	public UserSkillID() {
	
	}

	public UserSkillID(int userId, int skillId) {
		super();
		this.userId = userId;
		this.skillId = skillId;
	}

	@Column(name="user_id")
	private int userId;
	
	@Column(name="skill_id")
	private int skillId;
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(skillId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSkillID other = (UserSkillID) obj;
		return skillId == other.skillId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserSkillID [userId=" + userId + ", skillId=" + skillId + "]";
	}
	
	
}
