package com.skilldistillery.barter.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User {
	
	// PARAMS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private boolean enabled;
	
	private String role;
	
	@Column(name="image_url")
	private String imageURL;
	
	private String email;

	@OneToOne
	@JoinColumn(name="ranking_id")
	private Ranking ranking;
	
	@ManyToMany
	@JoinTable(name = "user_has_friends", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private Set<User> friends; 
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <Comment> comments;
	
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <Task> tasks;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <TaskMessage> taskMessages;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <Notification> notifications;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <Complaint> complaints;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List <Post> posts;
	
	private String firstname;

	private String lastname;

	private String availability;

	private String phone;

	private String biography;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name="update_date")
	private LocalDateTime updatedDate;
	
	
	
	
	

	//CTOR
	public User () {}
	
	//GETTERS/SETTERS


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> task) {
		this.tasks = task;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public List<TaskMessage> getTaskMessages() {
		return taskMessages;
	}

	public void setTaskMessages(List<TaskMessage> taskMessages) {
		this.taskMessages = taskMessages;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}



	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		User other = (User) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + "]";
	}
	
	

}
