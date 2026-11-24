package com.GSBScholarship_API.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "online_addscholamount")
public class AddSchoolAmount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private  int id;
	
	@Column(name="course")
	private String course;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="syear")
	private String syear;
	
	
	public AddSchoolAmount(String course, String syear) {
		super();
		this.course = course;
		this.syear = syear;
	}

	public AddSchoolAmount(String course) {
		super();
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSyear() {
		return syear;
	}

	public void setSyear(String syear) {
		this.syear = syear;
	}

	public AddSchoolAmount() {
		super();
		// TODO Auto-generated constructor stub
	}
}
