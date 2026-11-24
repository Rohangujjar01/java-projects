package com.GSBBackend.dto;
import java.util.Date;

public class ReviewHistoryDto {

	private Integer statusHistoryId;
	

	private Integer user;
	
	
	private Long profile; 
	
	
	private String  status;
	
	
	private String  comments;
	
	private String  reviewedBy;
	
	
	private String  createdBy;
	
	
	private Date  createdDatetime;


	public Integer getStatusHistoryId() {
		return statusHistoryId;
	}


	public void setStatusHistoryId(Integer statusHistoryId) {
		this.statusHistoryId = statusHistoryId;
	}


	public Integer getUser() {
		return user;
	}


	public void setUser(Integer user) {
		this.user = user;
	}
	
	public Long getProfile() {
		return profile;
	}


	public void setProfile(Long profile) {
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


	public String getReviewedBy() {
		return reviewedBy;
	}


	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
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
