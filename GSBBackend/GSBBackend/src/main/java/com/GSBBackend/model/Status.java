package com.GSBBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="status")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	private Integer statusId;
	
	@Column(name="status")
	private String status;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
