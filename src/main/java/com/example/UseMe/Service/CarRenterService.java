package com.example.UseMe.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.UseMe.Dto.CarRenterSignUp;
import com.example.UseMe.Dto.CarRenterUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.CarRenter;


@Service
public interface CarRenterService {

	
	public CarRenter findByRenterId(String renterId);
	
	public CarRenter findByRenterFirstName(String renterFirstName);
	
	public CarRenter findByRenterPhoneNo(String renterPhoneNo);
	
	public Collection<CarRenter> findAll();
	
	public ServerResponse createCarRenter(String userCode, CarRenterSignUp renterSignUp);
	
	public ServerResponse updateCarRenter(String renterId,CarRenterUpdate renterUpdate);
	
	public ServerResponse rentCar(String renterId, String carId);
	
	public ServerResponse viewAll();
	
	public ServerResponse viewByRenterPhoneNo(String renterPhoneNo);
	
	public ServerResponse deleteRenter(String renterId);
	
	
}
