package com.example.UseMe.Dto;

import com.example.UseMe.Enum.CarUsage;

public class CarUpdate {

	
	
	private String carColor;
	private String carBrand;
	private String carLocation;
	private String carUsage;
	private String carRegNo;
	private String carMakeYear;
	private CarUsage carUsageType;
	
	
	
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
	public CarUsage getCarUsageType() {
		return carUsageType;
	}
	public void setCarUsageType(CarUsage carUsageType) {
		this.carUsageType = carUsageType;
	}
	
	
	
	
}
