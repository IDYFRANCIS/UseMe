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
@Table(name = "CarOwner")
public class CarOwner implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "owner_id", nullable = false, unique = true)
	private String ownerId;
	
	@Column(name = "ownerFirstName")
	private String ownerFirstName;
	
	@Column(name = "ownerLastName")
	private String ownerLastName;
	
	@Column(name = "ownerMiddleName")
	private String ownerMiddleName;
	
	@Column(name = "ownerGender")
	private String ownerGender;
	
	@Column(name = "ownerAddress")
	private String ownerAddress;
	
	@Column(name = "ownerPhoneNo")
	private String ownerPhoneNo;
	
	@Column(name = "ownerD.O.B")
	private String ownerDateOfBirth;
	
	@Column(name = "ownerEmail")
	private String ownerEmail;

	
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerMiddleName() {
		return ownerMiddleName;
	}

	public void setOwnerMiddleName(String ownerMiddleName) {
		this.ownerMiddleName = ownerMiddleName;
	}

	public String getOwnerGender() {
		return ownerGender;
	}

	public void setOwnerGender(String ownerGender) {
		this.ownerGender = ownerGender;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}

	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}

	public String getOwnerDateOfBirth() {
		return ownerDateOfBirth;
	}

	public void setOwnerDateOfBirth(String ownerDateOfBirth) {
		this.ownerDateOfBirth = ownerDateOfBirth;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
	
	
	
}
