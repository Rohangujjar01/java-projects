package com.GSBScholarship_API.dto;

import java.util.Date;

public class ReviewHistoryDto {

   private Integer statusHistoryId;
	

	private Integer user;
	
	
	private Integer student; 
	
	
	private String  status;
	
	
	private String remarks;
	
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


	public Integer getStudent() {
		return student;
	}


	public void setStudent(Integer student) {
		this.student = student;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
