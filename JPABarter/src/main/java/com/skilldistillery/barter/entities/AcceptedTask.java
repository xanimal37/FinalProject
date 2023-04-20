package com.skilldistillery.barter.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="accepted_task")
public class AcceptedTask {
	
	public AcceptedTask() {}
	
	@EmbeddedId
	private AcceptedTaskId acceptedTaskId;
	
	@Column(name="rating_by_requester")
	private Integer ratingByRequestor;
	
	@Column(name="rating_by_acceptor")
	private Integer ratingByAcceptor;
	
	@Column(name="remarks_by_requester")
	private String remarksByRequestor;
	
	@Column(name="remarks_by_acceptor")
	private String remarksByAcceptor;
	
	@Column(name="accept_date")
	private LocalDateTime acceptDate;

	public AcceptedTaskId getId() {
		return acceptedTaskId;
	}

	public void setId(AcceptedTaskId id) {
		this.acceptedTaskId = id;
	}

	public Integer getRatingByRequestor() {
		return ratingByRequestor;
	}

	public void setRatingByRequestor(Integer ratingByRequestor) {
		this.ratingByRequestor = ratingByRequestor;
	}

	public Integer getRatingByAcceptor() {
		return ratingByAcceptor;
	}

	public void setRatingByAcceptor(Integer ratingByAcceptor) {
		this.ratingByAcceptor = ratingByAcceptor;
	}

	public String getRemarksByRequestor() {
		return remarksByRequestor;
	}

	public void setRemarksByRequestor(String remarksByRequestor) {
		this.remarksByRequestor = remarksByRequestor;
	}

	public String getRemarksByAcceptor() {
		return remarksByAcceptor;
	}

	public void setRemarksByAcceptor(String remarksByAcceptor) {
		this.remarksByAcceptor = remarksByAcceptor;
	}

	public LocalDateTime getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(LocalDateTime acceptDate) {
		this.acceptDate = acceptDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acceptedTaskId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcceptedTask other = (AcceptedTask) obj;
		return Objects.equals(acceptedTaskId, other.acceptedTaskId);
	}

	@Override
	public String toString() {
		return "AcceptedTask [id=" + acceptedTaskId + ", ratingByRequestor=" + ratingByRequestor + ", ratingByAcceptor="
				+ ratingByAcceptor + ", remarksByRequestor=" + remarksByRequestor + ", remarksByAcceptor="
				+ remarksByAcceptor + ", acceptDate=" + acceptDate + "]";
	}
	
	
	
	
	
	

}
