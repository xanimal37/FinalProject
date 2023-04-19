package com.skilldistillery.barter.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AcceptedTaskId implements Serializable{
	
	public AcceptedTaskId() {}

	private static final long serialVersionUID = 1L;
	
	@Column(name="task_id")
	private int taskId;
	
	@Column(name="acceptor_id")
	private int acceptorId;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getAcceptorId() {
		return acceptorId;
	}

	public void setAcceptorId(int acceptorId) {
		this.acceptorId = acceptorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acceptorId, taskId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcceptedTaskId other = (AcceptedTaskId) obj;
		return acceptorId == other.acceptorId && taskId == other.taskId;
	}

	@Override
	public String toString() {
		return "AcceptedTaskId [taskId=" + taskId + ", acceptorId=" + acceptorId + "]";
	}
	
}
