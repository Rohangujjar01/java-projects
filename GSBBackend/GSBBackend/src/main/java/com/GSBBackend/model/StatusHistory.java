package com.GSBBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="status_history")
public class StatusHistory {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_history_id")
	private Integer statusHistoryId;
	
	@JsonBackReference
	@ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName = "user_id")
	private User user;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Profile.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="profile_id",referencedColumnName = "id")
	private Profile profile; 
	
	
	@Column(name = "status")
	private String  status;
	
	@Column(name = "comments")
	private String  comments;
	
	@Column(name = "created_by")
	private String  createdBy;
	
	@Column(name = "created_datetime")
	private Date  createdDatetime;

	public Integer getStatusHistoryId() {
		return statusHistoryId;
	}

	public void setStatusHistoryId(Integer statusHistoryId) {
		this.statusHistoryId = statusHistoryId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}	
}
