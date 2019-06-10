package com.example.UseMe.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name = "CarRenter")
public class CarRenter implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "renter_id", nullable = false, unique = true)
	private String renterId;
	
	@Column(name = "renterFirstName")
	private String renterFirstName;
	
	@Column(name = "renterMiddleName")
	private String renterMiddleName;
	
	@Column(name = "renterLastName")
	private String RenterLastName;
	
	@Column(name = "renterGender")
	private String renterGender;
	
	@Column(name = "renterAddress")
	private String renterAddress;
	
	@Column(name = "renterPhoneNo")
	private String renterPhoneNo;
	
	@Column(name = "renterD.O.B")
	private String renterDateOfBirth;
	
	@Column(name = "renterEmail")
	private String renterEmail;

	
	
	public String getRenterId() {
		return renterId;
	}

	public void setRenterId(String renterId) {
		this.renterId = renterId;
	}

	public String getRenterFirstName() {
		return renterFirstName;
	}

	public void setRenterFirstName(String renterFirstName) {
		this.renterFirstName = renterFirstName;
	}

	public String getRenterMiddleName() {
		return renterMiddleName;
	}

	public void setRenterMiddleName(String renterMiddleName) {
		this.renterMiddleName = renterMiddleName;
	}

	public String getRenterLastName() {
		return RenterLastName;
	}

	public void setRenterLastName(String renterLastName) {
		RenterLastName = renterLastName;
	}

	public String getRenterGender() {
		return renterGender;
	}

	public void setRenterGender(String renterGender) {
		this.renterGender = renterGender;
	}

	public String getRenterAddress() {
		return renterAddress;
	}

	public void setRenterAddress(String renterAddress) {
		this.renterAddress = renterAddress;
	}

	public String getRenterPhoneNo() {
		return renterPhoneNo;
	}

	public void setRenterPhoneNo(String renterPhoneNo) {
		this.renterPhoneNo = renterPhoneNo;
	}

	public String getRenterDateOfBirth() {
		return renterDateOfBirth;
	}

	public void setRenterDateOfBirth(String renterDateOfBirth) {
		this.renterDateOfBirth = renterDateOfBirth;
	}

	public String getRenterEmail() {
		return renterEmail;
	}

	public void setRenterEmail(String renterEmail) {
		this.renterEmail = renterEmail;
	}
	
	
	

}
