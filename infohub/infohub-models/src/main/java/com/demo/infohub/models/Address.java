package com.demo.infohub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(name = "ADDRESS", unique = false, nullable = true)
	private String address;

	@Column(name = "CITY", nullable = true)
	private String city;

	@Column(name = "STATE", nullable = true)
	private String state;

	@Column(name = "COUNTRY", nullable = true)
	private String country;

	@Column(name = "ZIP", nullable = true)
	private String zip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
