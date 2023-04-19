package com.skilldistillery.barter.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	
	public Task() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@Column(name="estimated_hours")
	private Integer estimatedHours;
	@Column(name="materials_provided")
	private Boolean materialsProvided;
	@Column(name="schedule_date")
	private LocalDateTime scheduleDate;
	@Column(name="start_date")
	private LocalDateTime startDate;
	@Column(name="complete_date")
	private LocalDateTime completeDate;
	@Column(name="create_date")
	private LocalDateTime createDate;
	@Column(name="update_date")
	private LocalDateTime updateDate;
	//relationships
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
	public Integer getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public Boolean getMaterialsProvided() {
		return materialsProvided;
	}
	public void setMaterialsProvided(Boolean materialsProvided) {
		this.materialsProvided = materialsProvided;
	}
	public LocalDateTime getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(LocalDateTime scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(LocalDateTime completeDate) {
		this.completeDate = completeDate;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
