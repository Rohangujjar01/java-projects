package com.GSBScholarship_API.dto;

import java.util.List;

public class ApplicationStatusCountResponse {
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private List<ApplicationStatusCountDTO> applicationStatusCountList;
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
	public List<ApplicationStatusCountDTO> getApplicationStatusCountList() {
		return applicationStatusCountList;
	}
	public void setApplicationStatusCountList(List<ApplicationStatusCountDTO> applicationStatusCountList) {
		this.applicationStatusCountList = applicationStatusCountList;
	}
}
