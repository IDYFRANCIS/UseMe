package com.example.UseMe.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.UseMe.Dto.CarOwnerSignUp;
import com.example.UseMe.Dto.CarOwnerUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.CarOwner;


@Service
public interface CarOwnerService {

	
	public CarOwner findByOwnerId(String OwnerId);
	
	public CarOwner findByOwnerFirstName(String ownerFirstName);
	
	public CarOwner findByPhoneNo(String ownerPhoneNo);
	
	public ServerResponse viewByOwnerPhoneNo(String ownerPhoneNo);
	
	public Collection<CarOwner> findAll();
	
	public ServerResponse updateCarOwner(String ownerId, CarOwnerUpdate ownerUpdate);
	
	public ServerResponse createCarOwner(CarOwnerSignUp ownerSignUp);
	
	public ServerResponse deleteOwner(String ownerId);
	
	public ServerResponse viewAll();
	
}
