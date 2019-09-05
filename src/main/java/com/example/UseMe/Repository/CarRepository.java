package com.example.UseMe.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Enum.CarUsage;
import com.example.UseMe.Model.Car;


@Repository
public interface CarRepository extends CrudRepository<Car, String>{

	public Car findByCarId(String carId);
	
	public Car findByCarColor(String carColor);
	
	public Collection<Car> findByCarUsageType(CarUsage carUsageType);
	
	public Car findByCarBrandIgnoreCase(String carBrand);
	
	public Car findByCarLocation(String carLocation);
	
	public Car findByCarMakeYear(String carMakeYear);
	
	public List<Car> findByCarBrandLike(String carBrand);
	
	public Collection<Car> findAll();
	
	public Car findByCarRegNo(String carRegNo);
	
	 @Query("SELECT A FROM Car A WHERE A.carBrand=:carBrand AND A.carMakeYear=:carMakeYear")
     public Collection<Car> findAllByCarBrandAndMakeYear(@Param("carBrand") String carBrand, @Param("carMakeYear")  String carMakeYear);

	
}
