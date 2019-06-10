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
import com.example.UseMe.Model.CarOwner;
import com.example.UseMe.Model.CarRenter;
import com.example.UseMe.Repository.CarRenterRepository;
import com.example.UseMe.Service.CarRenterService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class CarRenterServiceImpl implements CarRenterService{

	@Autowired
	private CarRenterRepository renterRepo;
	
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
	public ServerResponse createCarRenter(CarRenterSignUp renterSignUp) {
		
		
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
				
				response.setData("Invalid Renter email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(renterPhoneNo)) {
				response.setData("Invalid Car Renter phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarRenter renterRequest = findByRenterPhoneNo(renterPhoneNo);
				
				if (renterRequest != null) {
					response.setData("Car Renter already exist");
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
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

	            response.setData(renter);
	            response.setStatus(ServerResponseStatus.CREATED);
	            
			} catch (Exception e) {
			  response.setData("Failed to create car renter account");
	          response.setStatus(ServerResponseStatus.FAILED);

	          e.printStackTrace();
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
				
				response.setData("Invalid Car Renter email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(renterPhoneNo)) {
				response.setData("Invalid Car Renter phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarRenter updateRenter = findByRenterId(renterId);
				
				if (updateRenter == null) {
					response.setData("Car Owner does not exist");
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
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("Failed to update car renter account");
			            response.setStatus(ServerResponseStatus.FAILED);

			            e.printStackTrace();
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
    	 			
    	 			if (renter == null) {
    	 				response.setData("No car renter available");
    	 				response.setStatus(ServerResponseStatus.FAILED);
    	 				
    	 				return response;
    	 			}
    	 			
    	 			response.setData(renter);
    	 			response.setStatus(ServerResponseStatus.OK);
    	 			
    	 		} catch (Exception e) {
    	 			
    	 			response.setStatus(ServerResponseStatus.FAILED);
    	         	response.setData("Failed fetching all car renter accounts");
    	             e.printStackTrace();
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
   				
   				response.setData("Car Renter does not exist");
   				response.setStatus(ServerResponseStatus.FAILED);
   				
   				return response;
   			}
   			
   			response.setData(renter);
   			response.setStatus(ServerResponseStatus.OK);
   			
   		} catch (Exception e) {
   			
   			response.setStatus(ServerResponseStatus.FAILED);
           	response.setData("Failed fetching car renter account");
               e.printStackTrace();
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
 			response.setData("Car Renter 'ID' can not be null");
 			response.setStatus(ServerResponseStatus.FAILED);
 				
 			return response;
 		}
 		
 		try {
 			
 			CarRenter renters = renterRepo.findByRenterId(renterId);
 			
 			if (renters == null) {
 				response.setData("Car renter does not exist");
 				response.setStatus(ServerResponseStatus.FAILED);
 				
 				return response;
 			}
 			
 			CarRenter renter = entityManager.find(CarRenter.class, renters.getRenterId());
 			
 			
 			entityManager.remove(renter);
 			entityManager.flush();
 			
 			 response.setStatus(ServerResponseStatus.DELETED);
 			 response.setData("Car Renter account has been successfully deleted");

 	        } catch (Exception e) {
 	        	response.setStatus(ServerResponseStatus.FAILED);
 	        	response.setData("Failed to delete car renter account");
 	            e.printStackTrace();
 	        }
 		
 		return response;
 	    
           }
       
       

    }




	
