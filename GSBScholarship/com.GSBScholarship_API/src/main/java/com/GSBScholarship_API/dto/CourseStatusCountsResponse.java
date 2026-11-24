package com.GSBScholarship_API.dto;

import java.util.List;

public class CourseStatusCountsResponse {
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private List<CourseStatusCountsDTO> courseStatusCountsList;
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
	public List<CourseStatusCountsDTO> getCourseStatusCountsList() {
		return courseStatusCountsList;
	}
	public void setCourseStatusCountsList(List<CourseStatusCountsDTO> courseStatusCountsList) {
		this.courseStatusCountsList = courseStatusCountsList;
	}
	
}
