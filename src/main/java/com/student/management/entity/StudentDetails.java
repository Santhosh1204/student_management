package com.student.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_Details")
public class StudentDetails {
	
	@Id
	@Column(name="Name")
	private String name;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Pincode")
	private String pincode;
	
	@Column(name="SAT_SCORE")
	private int satScore;
	
	@Column(name="Result")
	private String result;
	
	// Default constructor
	public StudentDetails() {
		super();
	}
	
	// Parameterized constructor
	public StudentDetails(String name, String address, String city, String country, String pincode, int satScore,
			String result) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.satScore = satScore;
		this.result = result;
	}

	// Getter and Setter methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getSatScore() {
		return satScore;
	}

	public void setSatScore(int satScore) {
		this.satScore = satScore;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	// toString method for easy object representation
	@Override
	public String toString() {
		return "StudentDetails [name=" + name + ", address=" + address + ", city=" + city + ", country=" + country
				+ ", pincode=" + pincode + ", satScore=" + satScore + ", result=" + result + "]";
	}
	
}
