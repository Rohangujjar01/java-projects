package com.GSBScholarship_API.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "online_cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id")
	private  int id;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="city_state")
	private String cityState;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityState() {
		return cityState;
	}

	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", cityState=" + cityState + "]";
	}	
}
