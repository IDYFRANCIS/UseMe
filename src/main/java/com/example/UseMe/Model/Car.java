package com.example.UseMe.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name = "Car")
public class Car implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "car_id", nullable = false, unique = true)
	private String carId;
	
	@Column
	private String carOwnerEmail;
	
	@Column
	private String carOwnerPhoneNo;
	
	@Column
	private String carOwnerFirstName;
	
	@Column
	private String carOwnerLastName;
	
	@Column
	private String carOwnerAddress;
	
	@Column
	private String carOwnerGender;
	
	@Column
	private String carColor;
	
	@Column
	private String carBrand;
	
	@Column
	private String carLocation;
	
	@Column
	private String carUsage;
	
	@Column
	private String carRegNo;
	
	@Column
	private String carMakeYear;

	
	
	
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarOwnerEmail() {
		return carOwnerEmail;
	}

	public void setCarOwnerEmail(String carOwnerEmail) {
		this.carOwnerEmail = carOwnerEmail;
	}

	public String getCarOwnerPhoneNo() {
		return carOwnerPhoneNo;
	}

	public void setCarOwnerPhoneNo(String carOwnerPhoneNo) {
		this.carOwnerPhoneNo = carOwnerPhoneNo;
	}

	public String getCarOwnerFirstName() {
		return carOwnerFirstName;
	}

	public void setCarOwnerFirstName(String carFirstOwnerName) {
		this.carOwnerFirstName = carFirstOwnerName;
	}

	public String getCarOwnerLastName() {
		return carOwnerLastName;
	}

	public void setCarOwnerLastName(String carLastLastName) {
		this.carOwnerLastName = carLastLastName;
	}

	public String getCarOwnerAddress() {
		return carOwnerAddress;
	}

	public void setCarOwnerAddress(String carOwnerAddress) {
		this.carOwnerAddress = carOwnerAddress;
	}

	public String getCarOwnerGender() {
		return carOwnerGender;
	}

	public void setCarOwnerGender(String carOwnerGender) {
		this.carOwnerGender = carOwnerGender;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarLocation() {
		return carLocation;
	}

	public void setCarLocation(String carLocation) {
		this.carLocation = carLocation;
	}

	public String getCarUsage() {
		return carUsage;
	}

	public void setCarUsage(String carUsage) {
		this.carUsage = carUsage;
	}

	public String getCarRegNo() {
		return carRegNo;
	}

	public void setCarRegNo(String carRegNo) {
		this.carRegNo = carRegNo;
	}

	public String getCarMakeYear() {
		return carMakeYear;
	}

	public void setCarMakeYear(String carMakeYear) {
		this.carMakeYear = carMakeYear;
	}
	
	
	
	

}
