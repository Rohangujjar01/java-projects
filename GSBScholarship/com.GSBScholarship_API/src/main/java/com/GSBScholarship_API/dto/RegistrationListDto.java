package com.GSBScholarship_API.dto;

import com.GSBScholarship_API.entity.OnlineNewStudent;
import org.springframework.data.domain.Page;

public class RegistrationListDto {
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private Page<OnlineNewStudent> registrationList;
	
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
	public Page<OnlineNewStudent> getRegistrationList() {
		return registrationList;
	}
	public void setRegistrationList(Page<OnlineNewStudent> registrationList) {
		this.registrationList = registrationList;
	}	
}
