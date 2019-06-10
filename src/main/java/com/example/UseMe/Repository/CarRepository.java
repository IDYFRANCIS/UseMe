package com.example.UseMe.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Model.Car;


@Repository
public interface CarRepository extends CrudRepository<Car, String>{

	public Car findByCarId(String carId);
	
	public Car findByCarColor(String carColor);
	
	public Car findByCarBrand(String carBrand);
	
	public Car findByCarLocation(String carLocation);
	
	public Car findByCarMakeYear(String carMakeYear);
	
	public Car findByCarUsage(String carUsage);
	
	public Collection<Car> findAll();
	
	public Car findByCarRegNo(String carRegNo);
	
	public Car findByCarOwnerFirstName(String carOwnerFirstName);
	
	public Car findByCarOwnerPhoneNo(String carOwnerPhoneNo);
	
	public Car findByCarOwnerAddress(String carOwnerAddress);
	
	 @Query("SELECT A FROM Car A WHERE A.carBrand=:carBrand AND A.carMakeYear=:carMakeYear")
    public Collection<Car> findAllByCarBrandAndMakeYear(@Param("carBrand")String carBrand, @Param("carMakeYear")  String carMakeYear);

	
}
