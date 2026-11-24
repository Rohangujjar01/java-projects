package com.GSBScholarship_API.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "online_subcategory")
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="subid")
	private  int id;
	
	@Column(name="sub_name")
	private String subName;
	
	@Column(name="category")
	private String category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", subName=" + subName + ", category=" + category + "]";
	}	
}
