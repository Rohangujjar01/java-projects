package com.GSBScholarship_API.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.Date;



@Entity
@Table(name = "online_image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	@Column(name = "feeReceptPath")
	private String feeReceptPath;
	
	@Column(name = "marksheetPath")
	private String marksheetPath;
	
	@Column(name = "cancelledChequePath")
	private String cancelledChequePath;

	@JsonBackReference
	@ManyToOne
	private Student student;

	
	private Date insertion_date;
	
	@Column(name = "aadharcard_path")
	private String aadharcardPath;
	
	public String getAadharcardPath() {
		return aadharcardPath;
	}

	public void setAadharcardPath(String aadharcardPath) {
		this.aadharcardPath = aadharcardPath;
	}

	@Column(name = "certificate_path")
	private String certificatePath;


	public Image() {
	}

	public String getFeeReceptPath() {
		return feeReceptPath;
	}

	public void setFeeReceptPath(String feeReceptPath) {
		this.feeReceptPath = feeReceptPath;
	}

	public String getMarksheetPath() {
		return marksheetPath;
	}

	public void setMarksheetPath(String marksheetPath) {
		this.marksheetPath = marksheetPath;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getCancelledChequePath() {
		return cancelledChequePath;
	}

	public void setCancelledChequePath(String cancelledChequePath) {
		this.cancelledChequePath = cancelledChequePath;
	}
}
