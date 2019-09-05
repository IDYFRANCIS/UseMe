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


@SuppressWarnings("serial")
@Entity
@Table(name = "car_renter")
public class CarRenter implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "renter_id", nullable = false, unique = true)
	private String renterId;
	
	@Column(name = "renter_first_name")
	private String renterFirstName;
	
	@Column(name = "renter_middle_name")
	private String renterMiddleName;
	
	@Column(name = "renter_last_name")
	private String RenterLastName;
	
	@Column(name = "renter_gender")
	private String renterGender;
	
	@Column(name = "renter_address")
	private String renterAddress;
	
	@Column(name = "renter_phone_no")
	private String renterPhoneNo;
	
	@Column(name = "renter_d.o.b")
	private String renterDateOfBirth;
	
	@Column(name = "renter_emal")
	private String renterEmail;
    
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "owner_renter", joinColumns = @JoinColumn(name = "renter_id", referencedColumnName = "renter_id"), inverseJoinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "owner_id"))
	private Set<CarOwner> carOwner;
	
	//@JsonIgnore
    @OneToMany(mappedBy = "carRenter", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Car> car;
	
	
    
    
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

	public Set<Car> getCar() {
		return car;
	}

	public void setCar(Set<Car> car) {
		this.car = car;
	}

	public Set<CarOwner> getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(Set<CarOwner> carOwner) {
		this.carOwner = carOwner;
	}

	

}
