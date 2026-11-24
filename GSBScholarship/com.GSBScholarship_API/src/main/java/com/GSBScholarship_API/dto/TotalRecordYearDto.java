package com.GSBScholarship_API.dto;

public class TotalRecordYearDto {
	
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private Long totalApplications;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Long getTotalApplications() {
		return totalApplications;
	}
	public void setTotalApplications(Long totalApplications) {
		this.totalApplications = totalApplications;
	}
}
