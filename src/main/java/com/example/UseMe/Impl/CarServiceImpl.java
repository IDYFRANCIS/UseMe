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
import com.example.UseMe.Enum.CarUsage;
import com.example.UseMe.Model.Car;
import com.example.UseMe.Model.CarOwner;
import com.example.UseMe.Repository.CarOwnerRepository;
import com.example.UseMe.Repository.CarRepository;
import com.example.UseMe.Service.CarService;


@Service
@Transactional
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private CarOwnerRepository carOwnerRepo;
	
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
				return carRepo.findByCarBrandIgnoreCase(carBrand);
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
	public Collection<Car> findByCarUsage(CarUsage carUsage) {
		
		try {
			   return carRepo.findByCarUsageType(carUsage);
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


/*******************************************************************************************************************************************
 *                                              CREATING A CAR ACCOUNT
 *******************************************************************************************************************************************/
	@Override
	public ServerResponse createCar( String ownerId, CarSignUp carSignUp) {
		
		  ServerResponse response = new ServerResponse();
			
			Car cars = null;
			
		
			String carColor = carSignUp.getCarColor() != null ? carSignUp.getCarColor() : carSignUp.getCarColor();
			String carBrand = carSignUp.getCarBrand() != null ? carSignUp.getCarBrand() : carSignUp.getCarBrand();
			String carLocation = carSignUp.getCarLocation() != null ? carSignUp.getCarLocation() : carSignUp.getCarLocation();
			CarUsage  carUsage = carSignUp.getCarUsageType() != null ? carSignUp.getCarUsageType() : carSignUp.getCarUsageType();
			String carRegNo = carSignUp.getCarRegNo() != null ? carSignUp.getCarRegNo() : carSignUp.getCarRegNo();
			String carMakeYear = carSignUp.getCarMakeYear() != null ? carSignUp.getCarMakeYear() : carSignUp.getCarMakeYear();
			
			
			
			try {
			     Car signUpRequest = findByCarRegNo(carRegNo);
				
				if (signUpRequest != null) {
					response.setData("");
					response.setMessage("Car already exist");
					response.setSuccess(false);
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
				}
				
				CarOwner owner = carOwnerRepo.findByOwnerId(ownerId);
				
				if (owner == null) {
					response.setData("");
					response.setMessage("Car owner does not exist");
					response.setSuccess(false);
					response.setStatus(ServerResponseStatus.FAILED);
				}
				
				cars = new Car();
				
			    cars.setCarOwner(owner);
				cars.setCarColor(carColor);
				cars.setCarBrand(carBrand);
				cars.setCarLocation(carLocation);
				cars.setCarUsageType(carUsage);
				cars.setCarRegNo(carRegNo);
				cars.setCarMakeYear(carMakeYear);

				
				entityManager.persist(cars);

	            response.setData(cars);
	            response.setStatus(ServerResponseStatus.CREATED);
	            response.setSuccess(true);
	            response.setMessage("Car created successfully");
	             
			} catch (Exception e) {
			  response.setData("");
			  response.setMessage("Failed to create car");
			  response.setSuccess(false);
	          response.setStatus(ServerResponseStatus.FAILED);
	          e.printStackTrace();
	          return response;
	             
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
		    String carColor = carUpdate.getCarColor() != null ? carUpdate.getCarColor() : carUpdate.getCarColor();
			String carBrand = carUpdate.getCarBrand() != null ? carUpdate.getCarBrand() : carUpdate.getCarBrand();
			String carLocation = carUpdate.getCarLocation() != null ? carUpdate.getCarLocation() : carUpdate.getCarLocation();
			CarUsage carUsage = carUpdate.getCarUsageType() != null ?  carUpdate.getCarUsageType() : carUpdate.getCarUsageType();
			String carRegNo = carUpdate.getCarRegNo() != null ? carUpdate.getCarRegNo() : carUpdate.getCarRegNo();
			String carMakeYear = carUpdate.getCarMakeYear() != null ? carUpdate.getCarMakeYear() : carUpdate.getCarMakeYear();
			

			try {
			     Car updateRequest = findByCarId(carId);
				
				if (updateRequest == null) {
					response.setData("");
					response.setMessage("Car does not exist");
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
				}
				
				  cars = entityManager.find(Car.class, updateRequest.getCarId());
				   
				 
				   if (carColor != null) 
					   cars.setCarColor(carColor);
				   if (carBrand != null) 
					   cars.setCarBrand(carBrand);
				   if (carLocation != null) 
					   cars.setCarLocation(carLocation);
				   if (carUsage != null) 
				       cars.setCarUsageType(carUsage);
				   if (carRegNo != null) 
					   cars.setCarRegNo(carRegNo);
				   if (carMakeYear != null) 
					   cars.setCarMakeYear(carMakeYear);
					
		           response.setData(cars);
		           response.setMessage("Car details updated successfully");
		           response.setSuccess(true);
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("");
			            response.setMessage("Failed to update car details");
			            response.setSuccess(false);
			            response.setStatus(ServerResponseStatus.FAILED);
			            e.printStackTrace();
			            
			            return response;
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
			
			if (cars.size() < 1) {
				response.setData("");
				response.setMessage("Car list is empty");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.NOT_FOUND);
				
				return response;
			}
			
			response.setData(cars);
			response.setMessage("Cars fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("");
        	response.setMessage("Something went wrong");
        	response.setSuccess(false);
            e.printStackTrace();
            
            return response;
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
				response.setData("");
				response.setMessage("Car brand cannot be empty");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
				
			}
			
			if(carMakeYear == null) {
				response.setData("");
				response.setMessage("Car make year connot be empty");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			 cars = carRepo.findAllByCarBrandAndMakeYear(carBrand, carMakeYear);
			
			if(cars.size() < 1) {
				response.setData("");
				response.setMessage("Car with such brand and make year does not exist");
				response.setStatus(ServerResponseStatus.NOT_FOUND);
				response.setSuccess(false);
				
				return response;
			}
			
			response.setData(cars);
			response.setMessage("Car fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("");
        	response.setMessage("Failed fetching car details");
        	response.setSuccess(false);
            e.printStackTrace();
            return response;
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
				
				response.setData("");
				response.setMessage("Car brand not found");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			response.setData(cars);
			response.setMessage("Car fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("");
        	response.setMessage("Failed fetching car details");
        	response.setSuccess(false);
            e.printStackTrace();
            
            return response;
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
			response.setData("");
			response.setMessage("Car 'ID' can not be null");
			response.setStatus(ServerResponseStatus.FAILED);
				
			return response;
		}
		
		try {
			
			Car cars = carRepo.findByCarId(carId);
			
			if (cars == null) {
				response.setData("");
				response.setMessage("Car does not exist");
				response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			Car car = entityManager.find(Car.class, cars.getCarId());
			
			entityManager.remove(car);
			entityManager.flush();
			
			 response.setStatus(ServerResponseStatus.DELETED);
			 response.setData("Car account has been successfully deleted");
			 response.setMessage("Car account has been successfully deleted");
			 response.setSuccess(true);

	        } catch (Exception e) {
	        	response.setStatus(ServerResponseStatus.FAILED);
	        	response.setData("");
	        	response.setMessage("Failed to delete car account");
	        	response.setSuccess(false);
	            e.printStackTrace();
	            
	            return response;
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
			
			response.setData("");
			response.setMessage("Car reg no not found");
			response.setStatus(ServerResponseStatus.FAILED);
			
			return response;
		}
		
		response.setData(cars);
		response.setMessage("Car fetched successfully");
		response.setSuccess(true);
		response.setStatus(ServerResponseStatus.OK);
		
	} catch (Exception e) {
		
		response.setStatus(ServerResponseStatus.FAILED);
    	response.setData("");
    	response.setMessage("Failed fetching car by reg no");
    	response.setSuccess(false);
        e.printStackTrace();
        
        return response;
	}
	
	return response;
}
    
    
	
}
