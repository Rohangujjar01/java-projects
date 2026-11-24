package com.GSBScholarship_API.dto;

import java.util.List;

public class GenderRecordPercentageResponse {
	private int id;
	private String message;
	private String status;
	private int statusCode;
	private List<GenderRecordPercentageDTO> genderRecordPercentageResponse;
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
	public List<GenderRecordPercentageDTO> getGenderRecordPercentageResponse() {
		return genderRecordPercentageResponse;
	}
	public void setGenderRecordPercentageResponse(List<GenderRecordPercentageDTO> genderRecordPercentageResponse) {
		this.genderRecordPercentageResponse = genderRecordPercentageResponse;
	}
	
}
