package com.GSBBackend.dto;

import com.GSBBackend.model.Profile;
import org.springframework.data.domain.Page;

public class ProfileListDto {
	
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private Page<Profile> profiles;
	
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
	public Page<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(Page<Profile> profiles) {
		this.profiles = profiles;
	}	
}
