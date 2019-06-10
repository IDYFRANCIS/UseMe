package com.example.UseMe.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UseMe.Constants.ServerResponseStatus;
import com.example.UseMe.Dto.CarSignUp;
import com.example.UseMe.Dto.CarUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.Car;
import com.example.UseMe.Repository.CarRepository;
import com.example.UseMe.Service.CarService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepo;
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Car findByCarId(String carId) {
		
		try {
			  return carRepo.findByCarId(carId);
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Car findByBrand(String carBrand) {
			
			try {
				return carRepo.findByCarBrand(carBrand);
			} catch (Exception e) {

				e.printStackTrace();
			}
			return null;
			
	}

	
	@Override
	public Car findByCarMakeYear(String carMakeYear) {
		
		try {
			  return carRepo.findByCarMakeYear(carMakeYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByLocation(String carLocation) {
		
		try {
			   return carRepo.findByCarLocation(carLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByCarUsage(String carUsage) {
		
		try {
			   return carRepo.findByCarUsage(carUsage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Collection<Car> findAll(){
		
		try {
			return (Collection<Car>) carRepo.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Collection<Car> findAllByCarBrandAndMakeYear(String carBrand, String carMakeYear) {
		
		
		try {
			   return carRepo.findAllByCarBrandAndMakeYear(carBrand, carMakeYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByCarRegNo(String carRegNo) {
		
		try {
			  return carRepo.findByCarRegNo(carRegNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByCarOwnerFirstName(String carOwnerFirstName) {
		
		try {
			   return carRepo.findByCarOwnerFirstName(carOwnerFirstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByCarOwnerPhoneNo(String carOwnerPhoneNo) {
		
		try {
			   return carRepo.findByCarOwnerPhoneNo(carOwnerPhoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car findByCarOwnerAddress(String carOwnerAddress) {
		
		try {
			    return carRepo.findByCarOwnerAddress(carOwnerAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/*******************************************************************************************************************************************
 *                                              CREATING A CAR ACCOUNT
 *******************************************************************************************************************************************/
	@Override
	public ServerResponse createCar(CarSignUp carSignUp) {
		
		  ServerResponse response = new ServerResponse();
			
			Car cars = null;
			
			String carOwnerEmail = carSignUp.getCarOwnerEmail() != null ? carSignUp.getCarOwnerEmail() : carSignUp.getCarOwnerEmail();
			String carOwnerPhoneNo = carSignUp.getCarOwnerPhoneNo() != null ? carSignUp.getCarOwnerPhoneNo() : carSignUp.getCarOwnerPhoneNo();
			String carOwnerFirstName = carSignUp.getCarOwnerFirstName() != null ? carSignUp.getCarOwnerFirstName() : carSignUp.getCarOwnerFirstName();
			String carOwnerLastName = carSignUp.getCarOwnerLastName() != null ? carSignUp.getCarOwnerLastName() : carSignUp.getCarOwnerLastName();
			String carOwnerAddress = carSignUp.getCarOwnerAddress() != null ? carSignUp.getCarOwnerAddress() : carSignUp.getCarOwnerAddress();
			String carOwnerGender = carSignUp.getCarOwnerGender() != null ? carSignUp.getCarOwnerGender() : carSignUp.getCarOwnerGender();
			String carColor = carSignUp.getCarColor() != null ? carSignUp.getCarColor() : carSignUp.getCarColor();
			String carBrand = carSignUp.getCarBrand() != null ? carSignUp.getCarBrand() : carSignUp.getCarBrand();
			String carLocation = carSignUp.getCarLocation() != null ? carSignUp.getCarLocation() : carSignUp.getCarLocation();
			String carUsage = carSignUp.getCarUsage() != null ? carSignUp.getCarUsage() : carSignUp.getCarUsage();
			String carRegNo = carSignUp.getCarRegNo() != null ? carSignUp.getCarRegNo() : carSignUp.getCarRegNo();
			String carMakeYear = carSignUp.getCarMakeYear() != null ? carSignUp.getCarMakeYear() : carSignUp.getCarMakeYear();
			
			
			if (!Utility.isValidEmail(carOwnerEmail)) {
				
				response.setData("Invalid Car Owner email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(carOwnerPhoneNo)) {
				response.setData("Invalid Car Owner phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     Car signUpRequest = findByCarRegNo(carRegNo);
				
				if (signUpRequest != null) {
					response.setData("Car already exist");
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
				}
				
				cars = new Car();
				
				cars.setCarOwnerEmail(carOwnerEmail);
				cars.setCarOwnerPhoneNo(carOwnerPhoneNo);
				cars.setCarOwnerFirstName(carOwnerFirstName);
				cars.setCarOwnerLastName(carOwnerLastName);
				cars.setCarOwnerAddress(carOwnerAddress);
				cars.setCarOwnerGender(carOwnerGender);
				cars.setCarColor(carColor);
				cars.setCarBrand(carBrand);
				cars.setCarLocation(carLocation);
				cars.setCarUsage(carUsage);
				cars.setCarRegNo(carRegNo);
				cars.setCarMakeYear(carMakeYear);
				
				entityManager.persist(cars);

	            response.setData(cars);
	            response.setStatus(ServerResponseStatus.CREATED);
	            
			} catch (Exception e) {
			  response.setData("Failed to create car account");
	          response.setStatus(ServerResponseStatus.FAILED);

	          e.printStackTrace();
			}
			return response;
			
			
	}

/**********************************************************************************************************************************
 * 	                                  UPDATING CARS  ACCOUNT
 **********************************************************************************************************************************/

	@Override
	public ServerResponse updateCar(String carId, CarUpdate carUpdate) {
		  ServerResponse response = new ServerResponse();
			
			Car cars = null;
			
			String carOwnerEmail = carUpdate.getCarOwnerEmail() != null ? carUpdate.getCarOwnerEmail() : carUpdate.getCarOwnerEmail();
			String carOwnerPhoneNo = carUpdate.getCarOwnerPhoneNo() != null ? carUpdate.getCarOwnerPhoneNo() : carUpdate.getCarOwnerPhoneNo();
			String carOwnerFirstName = carUpdate.getCarOwnerFirstName() != null ? carUpdate.getCarOwnerFirstName() : carUpdate.getCarOwnerFirstName();
			String carOwnerLastName = carUpdate.getCarOwnerLastName() != null ? carUpdate.getCarOwnerLastName() : carUpdate.getCarOwnerLastName();
			String carOwnerAddress = carUpdate.getCarOwnerAddress() != null ? carUpdate.getCarOwnerAddress() : carUpdate.getCarOwnerAddress();
			String carOwnerGender = carUpdate.getCarOwnerGender() != null ? carUpdate.getCarOwnerGender() : carUpdate.getCarOwnerGender();
			String carColor = carUpdate.getCarColor() != null ? carUpdate.getCarColor() : carUpdate.getCarColor();
			String carBrand = carUpdate.getCarBrand() != null ? carUpdate.getCarBrand() : carUpdate.getCarBrand();
			String carLocation = carUpdate.getCarLocation() != null ? carUpdate.getCarLocation() : carUpdate.getCarLocation();
			String carUsage = carUpdate.getCarUsage() != null ? carUpdate.getCarUsage() : carUpdate.getCarUsage();
			String carRegNo = carUpdate.getCarRegNo() != null ? carUpdate.getCarRegNo() : carUpdate.getCarRegNo();
			String carMakeYear = carUpdate.getCarMakeYear() != null ? carUpdate.getCarMakeYear() : carUpdate.getCarMakeYear();
			
            if (!Utility.isValidEmail(carOwnerEmail)) {
				
				response.setData("Invalid Car Owner email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(carOwnerPhoneNo)) {
				response.setData("Invalid Car Owner phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			

			try {
			     Car updateRequest = findByCarId(carId);
				
				if (updateRequest == null) {
					response.setData("Car does not exist");
	                response.setStatus(ServerResponseStatus.NOT_FOUND);

	                return response;
				}
				
				  cars = entityManager.find(Car.class, updateRequest.getCarId());
				   
				   if (carOwnerEmail != null) 
					   cars.setCarOwnerEmail(carOwnerEmail);
				   if (carOwnerPhoneNo != null) 
					   cars.setCarOwnerPhoneNo(carOwnerPhoneNo);
				   if (carOwnerFirstName != null) 
					   cars.setCarOwnerFirstName(carOwnerFirstName);
				   if (carOwnerLastName != null) 
					   cars.setCarOwnerLastName(carOwnerLastName);
				   if (carOwnerAddress != null) 
					   cars.setCarOwnerAddress(carOwnerAddress);
				   if (carOwnerGender != null) 
					   cars.setCarOwnerGender(carOwnerGender);
				   if (carColor != null) 
					   cars.setCarColor(carColor);
				   if (carBrand != null) 
					   cars.setCarBrand(carBrand);
				   if (carLocation != null) 
					   cars.setCarLocation(carLocation);
				   if (carUsage != null) 
					   cars.setCarUsage(carUsage);
				   if (carRegNo != null) 
					   cars.setCarRegNo(carRegNo);
				   if (carMakeYear != null) 
					   cars.setCarMakeYear(carMakeYear);
					
		           response.setData(cars);
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("Failed to update car account");
			            response.setStatus(ServerResponseStatus.FAILED);

			            e.printStackTrace();
			        }
				
			        return response;
	}

/***************************************************************************************************************
 * 	                             VIEW ALL CARS 
 ***************************************************************************************************************/
	
    @Override
    public ServerResponse viewAll() {
    	
     ServerResponse response = new ServerResponse();
		
		Collection<Car> cars = null;
		
		try {
			
			cars = findAll();
			
			if (cars == null) {
				response.setData("No car available");
				response.setStatus(ServerResponseStatus.NO_CONTENT);
				
				return response;
			}
			
			response.setData(cars);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("Failed fetching user accounts");
            e.printStackTrace();
		}
		
		
		return response;	
	
	
      }

       
/***************************************************************************************************************
 *                         VIEW CARS BY CAR BRAND AND MAKE YEAR
 ***************************************************************************************************************/

	@Override
	public ServerResponse viewAllByCarBrandAndMakeYear(String carBrand, String carMakeYear) {
		ServerResponse response  = new ServerResponse();
		
		Collection<Car> cars = null;
		
		try {
			if(carBrand == null) {
				response.setData("Car brand cannot be empty");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
				
			}
			
			if(carMakeYear == null) {
				response.setData("Car make year connot be empty");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			 cars = carRepo.findAllByCarBrandAndMakeYear(carBrand, carMakeYear);
			
			if(cars.size() < 1) {
				response.setData("Car with such brand and make year does not exist");
				response.setStatus(ServerResponseStatus.OK);
				
				return response;
			}
			
			response.setData(cars);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("Failed fetching car details");
            e.printStackTrace();
		}
		
		return response;
	}

/***************************************************************************************************************
 *                            VIEW CAR BY BRAND
 ***************************************************************************************************************/
	

	@Override
	public ServerResponse viewByBrand(String carBrand) {
		
     ServerResponse response = new ServerResponse();
		
		Car cars;
		
		try {
			
			cars = findByBrand(carBrand);
			
			if (cars == null) {
				
				response.setData("Car brand not found");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			response.setData(cars);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("Failed fetching user account");
            e.printStackTrace();
		}
		
		
		return response;
	}

/***************************************************************************************************************
 *                             DELETE CAR BY ID
 ***************************************************************************************************************/
    @Override
     public ServerResponse delete(String carId) {
    	
         ServerResponse response = new ServerResponse();
		
		if (carId == null) {
			response.setData("Car 'ID' can not be null");
			response.setStatus(ServerResponseStatus.FAILED);
				
			return response;
		}
		
		try {
			
			Car cars = carRepo.findByCarId(carId);
			
			if (cars == null) {
				response.setData("Car does not exist");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			Car car = entityManager.find(Car.class, cars.getCarId());
			
			entityManager.remove(car);
			entityManager.flush();
			
			 response.setStatus(ServerResponseStatus.DELETED);
			 response.setData("Car account has been successfully deleted");

	        } catch (Exception e) {
	        	response.setStatus(ServerResponseStatus.FAILED);
	        	response.setData("Failed to delete car account");
	            e.printStackTrace();
	        }
		
		return response;
	    
       }

/************************************************************************************************************
 *                               GET CAR BY CAR REG NO
 ************************************************************************************************************/
    
    @Override
     public ServerResponse viewByRegNo(String carRegNo) {
	   
    	ServerResponse response = new ServerResponse();
    	
	Car cars;
	
	try {
		
		cars = findByCarRegNo(carRegNo);
		
		if (cars == null) {
			
			response.setData("Car reg no not found");
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		response.setData(cars);
		response.setStatus(ServerResponseStatus.OK);
		
	} catch (Exception e) {
		
		response.setStatus(ServerResponseStatus.FAILED);
    	response.setData("Failed fetching car by reg no");
        e.printStackTrace();
	}
	
	
	return response;
}
    
    
	
}
