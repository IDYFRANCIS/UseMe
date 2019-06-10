package com.example.UseMe.Repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Model.CarOwner;


@Repository
public interface CarOwnerRepository extends CrudRepository<CarOwner, String>{

	public CarOwner findByOwnerId(String ownerId);
	
	public CarOwner findByOwnerFirstName(String ownerFirstName);
	
	public CarOwner findByOwnerPhoneNo(String ownerPhoneNo);
	
	public Collection<CarOwner> findAll();
	
}
