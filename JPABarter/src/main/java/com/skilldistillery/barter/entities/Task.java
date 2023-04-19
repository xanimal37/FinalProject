package com.skilldistillery.barter.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="task_status_id")
	private TaskStatus taskStatus;
	
	@OneToMany(mappedBy="task")
	private List<TaskMessage> taskMessages;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany
	@JoinTable(name="task_has_skill",
			joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="skill_id")
			)
	private List<Skill> skills;
	
	//methods
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<TaskMessage> getTaskMessages() {
		return taskMessages;
	}
	
	public void setTaskMessages(List<TaskMessage> taskMessages) {
		this.taskMessages = taskMessages;
	}
	
	//add and remove messges
	public void addTaskMessage(TaskMessage taskMessage) {
		if(taskMessages==null) {
			taskMessages=new ArrayList<>();
			taskMessages.add(taskMessage);
		}
		if(taskMessage.getTask()!=null) {
			taskMessage.getTask().removeTaskMessage(taskMessage);
		}
		taskMessage.setTask(this);
	}
	
	public void removeTaskMessage(TaskMessage taskMessage) {
		if(taskMessage!=null && taskMessages.contains(taskMessage)) {
			taskMessages.remove(taskMessage);
			taskMessage.setTask(null);
		}
	}
	
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
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
