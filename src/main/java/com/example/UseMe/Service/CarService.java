package com.example.UseMe.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.UseMe.Dto.CarSignUp;
import com.example.UseMe.Dto.CarUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.Car;


@Service
public interface CarService {
	
	public Collection<Car> findAll();
	
	public Car findByCarId(String carId);
	
	public Car findByBrand(String carBrand);
	
	public Collection<Car> findAllByCarBrandAndMakeYear(String carBrand, String carMakeYear);
	
	public Car findByCarMakeYear(String carMakeYear);
	
	public Car findByLocation(String carLocation);
	
	public Car findByCarUsage(String carUsage);
	
	public ServerResponse viewAll();
	
	public Car findByCarRegNo(String carRegNo);
	
	public Car findByCarOwnerFirstName(String carOwnerFirstName);
	
	public Car findByCarOwnerPhoneNo(String carOwnerPhoneNo);
	
	public Car findByCarOwnerAddress(String carOwnerAddress);
	
	public ServerResponse delete(String carId);
	
	public ServerResponse createCar(CarSignUp carSignUp);
	
	public ServerResponse viewByBrand(String carBrand);
	
	public ServerResponse viewByRegNo(String carRegNo);
	
	public ServerResponse updateCar(String carId, CarUpdate carUpdate);
	
	public ServerResponse viewAllByCarBrandAndMakeYear(String carBrand, String carMakeYear);

	

}
