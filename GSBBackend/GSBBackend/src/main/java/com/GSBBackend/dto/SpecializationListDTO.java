package com.GSBBackend.dto;

import com.GSBBackend.model.Specialization;

import java.util.List;

public class SpecializationListDTO {
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private List<Specialization> specializationList;
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
	public List<Specialization> getSpecializationList() {
		return specializationList;
	}
	public void setSpecializationList(List<Specialization> specializationList) {
		this.specializationList = specializationList;
	}	
}
