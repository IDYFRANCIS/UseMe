package com.example.UseMe.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.UseMe.Constants.ServerResponseStatus;
import com.example.UseMe.Dto.CarRenterSignUp;
import com.example.UseMe.Dto.CarRenterUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.Car;
import com.example.UseMe.Model.CarRenter;
import com.example.UseMe.Model.User;
import com.example.UseMe.Repository.CarRenterRepository;
import com.example.UseMe.Repository.CarRepository;
import com.example.UseMe.Repository.UserRepository;
import com.example.UseMe.Service.CarRenterService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class CarRenterServiceImpl implements CarRenterService{

	@Autowired
	private CarRenterRepository renterRepo;
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public CarRenter findByRenterId(String renterId) {
		
		try {
			 return renterRepo.findByRenterId(renterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public CarRenter findByRenterFirstName(String renterFirstName) {
		
		try {
			 return renterRepo.findByRenterFirstName(renterFirstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	@Override
	public CarRenter findByRenterPhoneNo(String renterPhoneNo) {
		
		try {
			  return renterRepo.findByRenterPhoneNo(renterPhoneNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<CarRenter> findAll() {
		
		try {
			 return renterRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
/**********************************************************************************************
 * 	                               CREATING CAR RENTER ACCOUNT
 **********************************************************************************************/
	@Override
	public ServerResponse createCarRenter(String userCode, CarRenterSignUp renterSignUp) {
		
		  ServerResponse response = new ServerResponse();
			
			CarRenter renter = null;
			
			String renterEmail = renterSignUp.getRenterEmail() != null ? renterSignUp.getRenterEmail() : renterSignUp.getRenterEmail();
			String renterPhoneNo = renterSignUp.getRenterPhoneNo() != null ? renterSignUp.getRenterPhoneNo() : renterSignUp.getRenterPhoneNo();
			String renterFirstName = renterSignUp.getRenterFirstName() != null ? renterSignUp.getRenterFirstName() : renterSignUp.getRenterFirstName();
			String renterMiddleName = renterSignUp.getRenterMiddleName() != null ? renterSignUp.getRenterMiddleName() : renterSignUp.getRenterMiddleName();
			String renterLastName = renterSignUp.getRenterLastName() != null ? renterSignUp.getRenterLastName() : renterSignUp.getRenterLastName();
			String renterGender = renterSignUp.getRenterGender() != null ? renterSignUp.getRenterGender() : renterSignUp.getRenterGender();
			String renterAddress = renterSignUp.getRenterAddress() != null ? renterSignUp.getRenterAddress() : renterSignUp.getRenterAddress();
			String renterDateOfBirth = renterSignUp.getRenterDateOfBirth() != null ? renterSignUp.getRenterDateOfBirth() : renterSignUp.getRenterDateOfBirth();
			
			
			if (!Utility.isValidEmail(renterEmail)) {
				
				response.setData("");
				response.setMessage("Invalid Renter email address");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	          return response;
			}
			
			if (!Utility.isValidPhone(renterPhoneNo)) {
				response.setData("");
				response.setMessage("Invalid Car Renter phone number");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	          return response;
			}
			
			
			try {
			     CarRenter renterRequest = findByRenterPhoneNo(renterPhoneNo);
				
				if (renterRequest != null) {
					response.setData("");
					response.setMessage("Car Renter already exist");
					response.setSuccess(false);
	                response.setStatus(ServerResponseStatus.FAILED);

	              return response;
				}
				
				User uCode = userRepo.findByUserCode(userCode);
				
				if (uCode == null) {
					response.setData("");
					response.setMessage("User does not exist");
					response.setSuccess(false);
					response.setStatus(ServerResponseStatus.FAILED);
				}
				
				renter = new CarRenter();
				
				renter.setRenterEmail(renterEmail);
				renter.setRenterPhoneNo(renterPhoneNo);
				renter.setRenterFirstName(renterFirstName);
				renter.setRenterMiddleName(renterMiddleName);
				renter.setRenterLastName(renterLastName);
				renter.setRenterGender(renterGender);
				renter.setRenterAddress(renterAddress);
				renter.setRenterDateOfBirth(renterDateOfBirth);
				
				entityManager.persist(renter);
				//renterRepo.save(renter);

	          response.setData(renter);
	          response.setMessage("Car renter created successfully");
	          response.setSuccess(true);
	          response.setStatus(ServerResponseStatus.CREATED);
	          
			} catch (Exception e) {
			  response.setData("");
			  response.setMessage("An error occured");
			  response.setSuccess(false);
	          response.setStatus(ServerResponseStatus.FAILED);
	        e.printStackTrace();
	        return response;
			}
			
			return response;
		
	}

	
/**********************************************************************************************
 * 	                               UPDATING CAR RENTER ACCOUNT
 **********************************************************************************************/
	@Override
	public ServerResponse updateCarRenter(String renterId, CarRenterUpdate renterUpdate) {
		
		
		  ServerResponse response = new ServerResponse();
			
			CarRenter renter = null;
			
			String renterEmail = renterUpdate.getRenterEmail() != null ? renterUpdate.getRenterEmail() : renterUpdate.getRenterEmail();
			String renterPhoneNo = renterUpdate.getRenterPhoneNo() != null ? renterUpdate.getRenterPhoneNo() : renterUpdate.getRenterPhoneNo();
			String renterFirstName = renterUpdate.getRenterFirstName() != null ?renterUpdate.getRenterFirstName() : renterUpdate.getRenterFirstName();
			String renterMiddleName = renterUpdate.getRenterMiddleName() != null ? renterUpdate.getRenterMiddleName() : renterUpdate.getRenterMiddleName();
			String renterLastName = renterUpdate.getRenterLastName() != null ? renterUpdate.getRenterLastName() : renterUpdate.getRenterLastName();
			String renterGender = renterUpdate.getRenterGender() != null ? renterUpdate.getRenterGender() : renterUpdate.getRenterGender();
			String renterAddress = renterUpdate.getRenterAddress() != null ? renterUpdate.getRenterAddress() : renterUpdate.getRenterAddress();
			String renterDateOfBirth = renterUpdate.getRenterDateOfBirth() != null ? renterUpdate.getRenterDateOfBirth() : renterUpdate.getRenterDateOfBirth();
			
            if (!Utility.isValidEmail(renterEmail)) {
				
				response.setData("");
				response.setMessage("Invalid Car Renter email address");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(renterPhoneNo)) {
				response.setData("");
				response.setMessage("Invalid Car Renter phone number");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarRenter updateRenter = findByRenterId(renterId);
				
				if (updateRenter == null) {
					response.setData("");
					response.setMessage("Car Owner does not exist");
					response.setSuccess(false);
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
				}
				
				 renter = entityManager.find(CarRenter.class, updateRenter.getRenterId());
				   
				   if (renterEmail != null)
					   renter.setRenterEmail(renterEmail);
				   if (renterPhoneNo != null) 
					   renter.setRenterPhoneNo(renterPhoneNo);
				   if (renterFirstName != null) 
					   renter.setRenterFirstName(renterFirstName);
				   if (renterMiddleName != null) 
					   renter.setRenterMiddleName(renterMiddleName);
				   if (renterLastName != null) 
					   renter.setRenterLastName(renterLastName);
				   if (renterGender != null) 
					   renter.setRenterGender(renterGender);
				   if (renterAddress != null) 
					   renter.setRenterAddress(renterAddress);
				   if (renterDateOfBirth != null) 
					   renter.setRenterDateOfBirth(renterDateOfBirth);
				  
		           response.setData(renter);
		           response.setMessage("Car renter detail updated successfully");
		           response.setSuccess(true);
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("");
			            response.setMessage("An error occured");
			            response.setSuccess(false);
			            response.setStatus(ServerResponseStatus.FAILED);
			            e.printStackTrace();
			            
			            return response;
			        }
				
			        return response;
			        
	}

	
/***************************************************************************************************************
 * 	                              VIEW ALL CAR RENTERS
 **************************************************************************************************************/

       @Override
       public ServerResponse viewAll() {
    	   
    	   
    		 ServerResponse response = new ServerResponse();
    	 		
    	 		Collection<CarRenter> renter = null;
    	 		
    	 		try {
    	 			
    	 			renter = findAll();
    	 			
    	 			if (renter.size() < 1) {
    	 				response.setData("");
    	 				response.setMessage("Car renter's list is empty");
    	 				response.setSuccess(false);
    	 				response.setStatus(ServerResponseStatus.NOT_FOUND);
    	 				
    	 				return response;
    	 			}
    	 			
    	 			response.setData(renter);
    	 			response.setMessage("All car renters fetched successfully");
    	 			response.setSuccess(true);
    	 			response.setStatus(ServerResponseStatus.OK);
    	 			
    	 		} catch (Exception e) {
    	 			response.setData("");
    	 			response.setMessage("An error occured");
    	 			response.setSuccess(false);
    	 			response.setStatus(ServerResponseStatus.FAILED);
    	             e.printStackTrace();
    	             
    	             return response;
    	 		}
    	 		
    	 		return response;	
    	 	
    	   }

       
       
 /**************************************************************************************************************
  *                     RENT A CAR
  **************************************************************************************************************/
             
       @Override
       public ServerResponse rentCar(String renterId, String carId) {
    	   
    	   ServerResponse response = new ServerResponse();
   		
   		
           if (renterId == null || renterId.isEmpty()) {
    			
    			response.setData("");
    			response.setMessage("Please provide car renter details");
    			response.setSuccess(false);
    			response.setStatus(ServerResponseStatus.FAILED);
    			
    			return response;
    		}
    		
    		if (carId == null || carId.isEmpty()) {
    			
    			response.setData("");
    			response.setMessage("Please provide car details");
    			response.setSuccess(false);
    			response.setStatus(ServerResponseStatus.FAILED);
    			
    			return response;
    		}
    		
    		try {
    			CarRenter renter =  renterRepo.findByRenterId(renterId);
    			 
    			if (renter == null) {
    				response.setData("");
    				response.setMessage("Car renter not found");
    				response.setSuccess(false);
    				response.setStatus(ServerResponseStatus.FAILED);
    				
    				return response;
    			}
    			
    			Car car = carRepo.findByCarId(carId);
    			
    			if (car == null) {
    				response.setData("");
    				response.setMessage("Car not found");
    				response.setSuccess(false);
    				response.setStatus(ServerResponseStatus.FAILED);
    				
    				return response;
    			}
    			
    			renter.getCar().add(car);
    			car.setCarRenter(renter);
    			
    			response.setData(renter);
    			response.setMessage("Car assigned successfully to renter");
    			response.setSuccess(true);
    			response.setStatus(ServerResponseStatus.OK);
    			
    		}catch(Exception e) {
    			response.setData("");
    			response.setMessage("Failed to assign car");
    			response.setSuccess(false);
    			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
    			return response;
    		}
    		
    		return response;
    	   
       }
       
       
 /**************************************************************************************************************
  *                    VIEW RENTER BY PHONE NUMBER
  **************************************************************************************************************/
       
       @Override
       public ServerResponse viewByRenterPhoneNo(String renterPhoneNo) {
    	   
    	   ServerResponse response = new ServerResponse();
   		
   		CarRenter renter;
   		
   		try {
   			
   			renter = findByRenterPhoneNo(renterPhoneNo);
   			
   			if (renter == null) {
   				
   				response.setData("");
   				response.setMessage("Car Renter does not exist");
   				response.setSuccess(false);
   				response.setStatus(ServerResponseStatus.FAILED);
   				
   				return response;
   			}
   			
   			response.setData(renter);
   			response.setMessage("Renter fetched by phone number");
   			response.setSuccess(true);
   			response.setStatus(ServerResponseStatus.OK);
   			
   		} catch (Exception e) {
   			response.setData("");
   			response.setMessage("An error occured");
   			response.setSuccess(false);
   			response.setStatus(ServerResponseStatus.FAILED);
            e.printStackTrace();
               
            return response;
   		}
   		
   		return response;
   		
       }

/***************************************************************************************************************
 *                                  DELETE CAR RENTER BY ID
 ***************************************************************************************************************/
       
    @Override
      public ServerResponse deleteRenter(String renterId) {
    	
    	 ServerResponse response = new ServerResponse();
 		
 		if (renterId == null) {
 			response.setData("");
 			response.setMessage("Car Renter 'ID' can not be null");
 			response.setSuccess(false);
 			response.setStatus(ServerResponseStatus.FAILED);
 				
 			return response;
 		}
 		
 		try {
 			
 			CarRenter renters = renterRepo.findByRenterId(renterId);
 			
 			if (renters == null) {
 				response.setData("");
 				response.setMessage("Car renter does not exist");
 				response.setSuccess(false);
 				response.setStatus(ServerResponseStatus.FAILED);
 				
 				return response;
 			}
 			
 			CarRenter renter = entityManager.find(CarRenter.class, renters.getRenterId());
 			
 			
 			entityManager.remove(renter);
 			entityManager.flush();
 			
 			 response.setStatus(ServerResponseStatus.DELETED);
 			 response.setData("Car Renter account has been successfully deleted");
 			 response.setMessage("Car Renter account has been successfully deleted");
 			 response.setSuccess(true);

 	        } catch (Exception e) {
 	        	response.setStatus(ServerResponseStatus.FAILED);
 	        	response.setData("");
 	        	response.setMessage("Failed to delete car renter account");
 	        	response.setSuccess(false);
 	            e.printStackTrace();
 	            
 	            return response;
 	        }
 		
 		return response;
 	    
           }

       
       

 }




	
