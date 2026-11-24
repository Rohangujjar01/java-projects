package com.GSBScholarship_API.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "online_scategory")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="scid")
	private  int id;
	
	@Column(name="scname")
	private String categoryName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}	
}