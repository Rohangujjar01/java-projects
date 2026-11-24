package com.GSBScholarship_API.dto;

import java.util.Date;

public class StudentApplicantListDto {
private Integer srNo;
private String name;
private Date date;
private String category;
private String sclass;
private String mobileNo;
private String status;
private String remark;
private String receipt;
private String marksheet;
private String certificate;
private String studentAadharcard;
public Integer getSrNo() {
	return srNo;
}
public void setSrNo(Integer srNo) {
	this.srNo = srNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getSclass() {
	return sclass;
}
public void setSclass(String sclass) {
	this.sclass = sclass;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getReceipt() {
	return receipt;
}
public void setReceipt(String receipt) {
	this.receipt = receipt;
}
public String getMarksheet() {
	return marksheet;
}
public void setMarksheet(String marksheet) {
	this.marksheet = marksheet;
}
public String getCertificate() {
	return certificate;
}
public void setCertificate(String certificate) {
	this.certificate = certificate;
}
public String getStudentAadharcard() {
	return studentAadharcard;
}
public void setStudentAadharcard(String studentAadharcard) {
	this.studentAadharcard = studentAadharcard;
}	
}
