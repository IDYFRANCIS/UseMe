package com.example.UseMe.Repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Model.CarRenter;


@Repository
public interface CarRenterRepository extends CrudRepository<CarRenter, String>{

	
	public CarRenter findByRenterFirstName(String renterFirstName);
	
	public CarRenter findByRenterPhoneNo(String renterPhoneNo);
	
	public CarRenter findByRenterId(String renterId);
	
	public Collection<CarRenter> findAll();
	

	
	
	
}
