package com.example.UseMe.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.UseMe.Enum.CarUsage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Entity
@Table(name = "car")
public class Car implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
	@Column(name = "car_id", nullable = false, unique = true)
	private String carId;
	
	
	@Column(name = "car_color")
	private String carColor;
	
	@Column(name = "car_brand")
	private String carBrand;
	
	@Column(name = "car_location")
	private String carLocation;
	
	@Column(name = "car_reg_no")
	private String carRegNo;
	
	@Column(name = "car_make_year")
	private String carMakeYear;
	
	@Column(name = "car_usage")
	private CarUsage carUsageType;
	
  //  @JsonIgnore
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private CarOwner carOwner;
    
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "renter_id")
    private CarRenter carRenter;

    
    
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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

	public CarUsage getCarUsageType() {
		return carUsageType;
	}

	public void setCarUsageType(CarUsage carUsageType) {
		this.carUsageType = carUsageType;
	}

	public CarOwner getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}

	public CarRenter getCarRenter() {
		return carRenter;
	}

	public void setCarRenter(CarRenter carRenter) {
		this.carRenter = carRenter;
	}
    
}
