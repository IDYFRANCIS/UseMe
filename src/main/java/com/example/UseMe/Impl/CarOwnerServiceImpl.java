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
import com.example.UseMe.Model.CarRenter;
import com.example.UseMe.Repository.CarOwnerRepository;
import com.example.UseMe.Service.CarOwnerService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class CarOwnerServiceImpl implements CarOwnerService{
	
	@Autowired
	private CarOwnerRepository ownerRepo;
	
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
	public ServerResponse createCarOwner(CarOwnerSignUp ownerSignUp) {
		

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
				
				response.setData("Invalid Owner email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(ownerPhoneNo)) {
				response.setData("Invalid Owner phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarOwner ownerRequest = findByPhoneNo(ownerPhoneNo);
				
				if (ownerRequest != null) {
					response.setData("Car Owner already exist");
	                response.setStatus(ServerResponseStatus.FAILED);

	                return response;
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
	            response.setStatus(ServerResponseStatus.CREATED);
	            
			} catch (Exception e) {
			  response.setData("Failed to create car owner account");
	          response.setStatus(ServerResponseStatus.FAILED);

	          e.printStackTrace();
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
				
				response.setData("Invalid Owner email address");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			if (!Utility.isValidPhone(ownerPhoneNo)) {
				response.setData("Invalid Owner phone number");
	            response.setStatus(ServerResponseStatus.FAILED);

	            return response;
			}
			
			
			try {
			     CarOwner updateOwner = findByOwnerId(ownerId);
				
				if (updateOwner == null) {
					response.setData("Car Owner does not exist");
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
		           response.setStatus(ServerResponseStatus.UPDATED);

			        } catch (Exception e) {
			            response.setData("Failed to update car owner account");
			            response.setStatus(ServerResponseStatus.FAILED);

			            e.printStackTrace();
			        }
				
			        return response;
	}

	
/***************************************************************************************************************
 * 	                    FIND CAR OWNER BY PHONE NUMBER
 ***************************************************************************************************************/
	
	@Override
	public ServerResponse  viewByOwnerPhoneNo(String ownerPhoneNo){
		ServerResponse response = new ServerResponse();
		
		
		try {
			
			CarOwner owner	= ownerRepo.findByOwnerPhoneNo(ownerPhoneNo);
			
			if (owner == null) {
				
				response.setData("Car Owner does not exist");
				response.setStatus(ServerResponseStatus.FAILED);
			 return response;
			}
			
			response.setData(owner);
			response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setStatus(ServerResponseStatus.FAILED);
        	response.setData("Failed fetching car owner account details");
            e.printStackTrace();
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
 			
 			if (owner == null) {
 				response.setData("No car owner available");
 				response.setStatus(ServerResponseStatus.FAILED);
 				
 				return response;
 			}
 			
 			response.setData(owner);
 			response.setStatus(ServerResponseStatus.OK);
 			
 		} catch (Exception e) {
 			
 			response.setStatus(ServerResponseStatus.FAILED);
         	response.setData("Failed fetching car owner accounts");
             e.printStackTrace();
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
  			response.setStatus(ServerResponseStatus.FAILED);
  				
  			return response;
  		}
  		
  		try {
  			
  			CarOwner owners = ownerRepo.findByOwnerId(ownerId);
  			
  			if (owners == null) {
  				response.setData("Car owner does not exist");
  				response.setStatus(ServerResponseStatus.FAILED);
  				
  				return response;
  			}
  			
  			CarOwner owner = entityManager.find(CarOwner.class, owners.getOwnerId());
  			
  			entityManager.remove(owner);
  			entityManager.flush();
  			
  			 response.setStatus(ServerResponseStatus.DELETED);
  			 response.setData("Car Owner account has been successfully deleted");

  	        } catch (Exception e) {
  	        	response.setStatus(ServerResponseStatus.FAILED);
  	        	response.setData("Failed to delete car owner account");
  	            e.printStackTrace();
  	        }
  		
  		return response;
         }
     
     

}
