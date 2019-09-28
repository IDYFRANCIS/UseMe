package com.example.UseMe.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Entity
@Table(name = "car_owner")
public class CarOwner implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "owner_id", nullable = false, unique = true)
	private String ownerId;
	
	@Column(name = "owner_first_name")
	private String ownerFirstName;
	
	@Column(name = "owner_last_name")
	private String ownerLastName;
	
	@Column(name = "owner_middle_name")
	private String ownerMiddleName;
	
	@Column(name = "owner_gender")
	private String ownerGender;
	
	@Column(name = "owner_address")
	private String ownerAddress;
	
	@Column(name = "owner_phone_no")
	private String ownerPhoneNo;
	
	@Column(name = "owner_d.o.b")
	private String ownerDateOfBirth;
	
	@Column(name = "owner_email")
	private String ownerEmail;
	
	@Column(name = "owner_role")
	private String role;

	@JsonIgnore
    @OneToMany(mappedBy = "carOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private  Set<Car> car;
	
    @JsonIgnore
	@ManyToMany(mappedBy = "carOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CarRenter> carRenter;
	
	
	
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
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Set<Car> getCar() {
		return car;
	}

	public void setCar(Set<Car> car) {
		this.car = car;
	}

	public Set<CarRenter> getCarRenter() {
		return carRenter;
	}

	public void setCarRenter(Set<CarRenter> carRenter) {
		this.carRenter = carRenter;
	}

	
}
