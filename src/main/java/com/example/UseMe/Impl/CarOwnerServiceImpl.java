package com.example.UseMe.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UseMe.Constants.ServerResponseStatus;
import com.example.UseMe.Dto.CarOwnerSignUp;
import com.example.UseMe.Dto.CarOwnerUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Model.CarOwner;
import com.example.UseMe.Model.User;
import com.example.UseMe.Repository.CarOwnerRepository;
import com.example.UseMe.Repository.CarRenterRepository;
import com.example.UseMe.Repository.UserRepository;
import com.example.UseMe.Service.CarOwnerService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class CarOwnerServiceImpl implements CarOwnerService{
	
	@Autowired
	private CarOwnerRepository ownerRepo;
	
	@Autowired
	private CarRenterRepository renterRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CarOwner findByOwnerId(String ownerId) {
		
		try {
			return ownerRepo.findByOwnerId(ownerId);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public CarOwner findByOwnerFirstName(String ownerFirstName) {
		
		try {
			  return ownerRepo.findByOwnerFirstName(ownerFirstName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarOwner findByPhoneNo(String ownerPhoneNo) {
		
		try {
			  return ownerRepo.findByOwnerPhoneNo(ownerPhoneNo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<CarOwner> findAll() {
		
		try {
			return (Collection<CarOwner>) ownerRepo.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	
/*************************************************************************************************************
 * 	                           CREATING CAR OWNER ACCOUNT
 *************************************************************************************************************/
	
	@Override
	public ServerResponse createCarOwner(String userCode, CarOwnerSignUp ownerSignUp) {
		

		  ServerResponse response = new ServerResponse();
			
			CarOwner owner = null;
			
			String ownerEmail = ownerSignUp.getOwnerEmail() != null ? ownerSignUp.getOwnerEmail() : ownerSignUp.getOwnerEmail();
			String ownerPhoneNo = ownerSignUp.getOwnerPhoneNo() != null ? ownerSignUp.getOwnerPhoneNo() : ownerSignUp.getOwnerPhoneNo();
			String ownerFirstName = ownerSignUp.getOwnerFirstName() != null ? ownerSignUp.getOwnerFirstName() : ownerSignUp.getOwnerFirstName();
			String ownerMiddleName = ownerSignUp.getOwnerMiddleName() != null ? ownerSignUp.getOwnerMiddleName() : ownerSignUp.getOwnerMiddleName();
			String ownerLastName = ownerSignUp.getOwnerLastName() != null ? ownerSignUp.getOwnerLastName() : ownerSignUp.getOwnerLastName();
			String ownerGender = ownerSignUp.getOwnerGender() != null ? ownerSignUp.getOwnerGender() : ownerSignUp.getOwnerGender();
			String ownerAddress = ownerSignUp.getOwnerAddress() != null ? ownerSignUp.getOwnerAddress() : ownerSignUp.getOwnerAddress();
			String ownerDateOfBirth = ownerSignUp.getOwnerDateOfBirth() != null ? ownerSignUp.getOwnerDateOfBirth() : ownerSignUp.getOwnerDateOfBirth();
			

			if (!Utility.isValidEmail(ownerEmail)) {
				
				response.setData("");
				response.setMessage("Invalid Owner email address");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(ownerPhoneNo)) {
				response.setData("");
				response.setMessage("Invalid Owner phone number");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarOwner ownerRequest = findByPhoneNo(ownerPhoneNo);
				
				if (ownerRequest != null) {
					response.setData("");
					response.setMessage("Car Owner already exist");
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
				
				owner = new CarOwner();
				
				owner.setOwnerEmail(ownerEmail);
				owner.setOwnerPhoneNo(ownerPhoneNo);
				owner.setOwnerFirstName(ownerFirstName);
				owner.setOwnerMiddleName(ownerMiddleName);
				owner.setOwnerLastName(ownerLastName);
				owner.setOwnerGender(ownerGender);
				owner.setOwnerAddress(ownerAddress);
				owner.setOwnerDateOfBirth(ownerDateOfBirth);
				
				entityManager.persist(owner);

	            response.setData(owner);
	            response.setMessage("Car owner created successfully");
	            response.setSuccess(true);
	            response.setStatus(ServerResponseStatus.CREATED);
	            
			} catch (Exception e) {
			  response.setData("");
			  response.setMessage("Failed to create car owner account");
			  response.setSuccess(false);
	          response.setStatus(ServerResponseStatus.FAILED);
	          e.printStackTrace();
	          
	          return response;
			}
			return response;
			
	}
	
	
/************************************************************************************************************
 * 	                           UPDATING CAR OWNER ACCOUNT
 ************************************************************************************************************/
	@Override
	public ServerResponse updateCarOwner(String ownerId, CarOwnerUpdate ownerUpdate) {
		
		
		  ServerResponse response = new ServerResponse();
			
			CarOwner owner = null;
			
			String ownerEmail = ownerUpdate.getOwnerEmail() != null ? ownerUpdate.getOwnerEmail() : ownerUpdate.getOwnerEmail();
			String ownerPhoneNo = ownerUpdate.getOwnerPhoneNo() != null ? ownerUpdate.getOwnerPhoneNo() : ownerUpdate.getOwnerPhoneNo();
			String ownerFirstName = ownerUpdate.getOwnerFirstName() != null ? ownerUpdate.getOwnerFirstName() : ownerUpdate.getOwnerFirstName();
			String ownerMiddleName = ownerUpdate.getOwnerMiddleName() != null ? ownerUpdate.getOwnerMiddleName() : ownerUpdate.getOwnerMiddleName();
			String ownerLastName = ownerUpdate.getOwnerLastName() != null ? ownerUpdate.getOwnerLastName() : ownerUpdate.getOwnerLastName();
			String ownerGender = ownerUpdate.getOwnerGender() != null ? ownerUpdate.getOwnerGender() : ownerUpdate.getOwnerGender();
			String ownerAddress = ownerUpdate.getOwnerAddress() != null ? ownerUpdate.getOwnerAddress() : ownerUpdate.getOwnerAddress();
			String ownerDateOfBirth = ownerUpdate.getOwnerDateOfBirth() != null ? ownerUpdate.getOwnerDateOfBirth() : ownerUpdate.getOwnerDateOfBirth();
			
            if (!Utility.isValidEmail(ownerEmail)) {
				
				response.setData("");
				response.setMessage("Invalid Owner email address");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(ownerPhoneNo)) {
				response.setData("");
				response.setMessage("Invalid Owner phone number");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarOwner updateOwner = findByOwnerId(ownerId);
				
				if (updateOwner == null) {
					response.setData("");
					response.setMessage("Car Owner does not exist");
					response.setSuccess(false);
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
				}
				
				 owner = entityManager.find(CarOwner.class, updateOwner.getOwnerId());
				   
				   if (ownerEmail != null)
					   owner.setOwnerEmail(ownerEmail);
				   if (ownerPhoneNo != null) 
					   owner.setOwnerPhoneNo(ownerPhoneNo);
				   if (ownerFirstName != null) 
					   owner.setOwnerFirstName(ownerFirstName);
				   if (ownerMiddleName != null) 
					   owner.setOwnerMiddleName(ownerMiddleName);
				   if (ownerLastName != null) 
					   owner.setOwnerLastName(ownerLastName);
				   if (ownerGender != null) 
					   owner.setOwnerGender(ownerGender);
				   if (ownerAddress != null) 
					   owner.setOwnerAddress(ownerAddress);
				   if (ownerDateOfBirth != null) 
					   owner.setOwnerDateOfBirth(ownerDateOfBirth);
				  
		           response.setData(owner);
		           response.setMessage("Car owner details updated successfully");
		           response.setSuccess(true);
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("");
			            response.setMessage("Failed to update car owner account");
			            response.setSuccess(false);
			            response.setStatus(ServerResponseStatus.FAILED);
			            e.printStackTrace();
			            
			            return response;
			        }
				
			        return response;
	}

	
/***************************************************************************************************************
 * 	                    FIND CAR OWNER BY PHONE NUMBER
 ***************************************************************************************************************/
	
	@Override
	public ServerResponse  viewByOwnerPhoneNo(String ownerPhoneNo){
		ServerResponse response = new ServerResponse();
		
		if(ownerPhoneNo.isEmpty()) {
			response.setData("");
			response.setMessage("Owner phone number cannot be empty");
			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.FAILED);
		}
		
		if (!Utility.isValidPhone(ownerPhoneNo)) {
			response.setData("");
			response.setMessage("Invalid Owner phone number");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		try {
			
			CarOwner owner	= ownerRepo.findByOwnerPhoneNo(ownerPhoneNo);
			
			if (owner == null) {
				
				response.setData("");
				response.setMessage("Car Owner does not exist");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
			 return response;
			}
			
			response.setData(owner);
			response.setMessage("Car owner fetched successfully");
			response.setSuccess(true);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("");
        	response.setMessage("Failed fetching car owner account details");
        	response.setSuccess(false);
            e.printStackTrace();
            
            return response;
		}
		
		
		return response;
	}

	
/***************************************************************************************************************
 * 	                        VIEW ALL CAR OWNERS
 **************************************************************************************************************/
     @Override
      public ServerResponse viewAll() {
    	 
    	 ServerResponse response = new ServerResponse();
 		
 		Collection<CarOwner> owner = null;
 		
 		try {
 			
 			owner = findAll();
 			
 			if (owner.size() < 1) {
 				response.setData("");
 				response.setMessage("Car owner's list is empty");
 				response.setSuccess(false);
 				response.setStatus(ServerResponseStatus.NOT_FOUND);
 				
 				return response;
 			}
 			
 			response.setData(owner);
 			response.setMessage("Car owners successfully fetched");
 			response.setSuccess(true);
 			response.setStatus(ServerResponseStatus.OK);
 			
 		} catch (Exception e) {
 			response.setData("");
 			response.setMessage("Failed fetching car owner accounts");
 			response.setStatus(ServerResponseStatus.FAILED);
         	response.setSuccess(false);
             e.printStackTrace();
             
             return response;
 		}
 		
 		
 		return response;	
 	
        }

/***************************************************************************************************************
 *                         DELETE CAR OWNER BY ID
 ***************************************************************************************************************/
     
     
     @Override
      public ServerResponse deleteOwner(String ownerId) {
    	 
    	 ServerResponse response = new ServerResponse();
  		
  		if (ownerId == null) {
  			response.setData("Car Owner 'ID' can not be null");
  			response.setSuccess(false);
  			response.setStatus(ServerResponseStatus.FAILED);
  				
  			return response;
  		}
  		
  		try {
  			
  			CarOwner owners = ownerRepo.findByOwnerId(ownerId);
  			
  			if (owners == null) {
  				response.setData("");
  				response.setMessage("Car owner does not exist");
  				response.setSuccess(false);
  				response.setStatus(ServerResponseStatus.FAILED);
  				
  				return response;
  			}
  			
  			CarOwner owner = entityManager.find(CarOwner.class, owners.getOwnerId());
  			
  			entityManager.remove(owner);
  			entityManager.flush();
  			 
  			 response.setMessage("Car Owner account has been successfully deleted");
  			 response.setStatus(ServerResponseStatus.DELETED);
  			 response.setData("Car Owner account has been successfully deleted");
  			 response.setSuccess(true);

  	        } catch (Exception e) {
  	        	response.setStatus(ServerResponseStatus.FAILED);
  	        	response.setData("");
  	        	response.setMessage("Failed to delete car owner account");
  	        	response.setSuccess(false);
  	            e.printStackTrace();
  	            
  	            return response;
  	        }
  		
  		return response;
         }


     

}
