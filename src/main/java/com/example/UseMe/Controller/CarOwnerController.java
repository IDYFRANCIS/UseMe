package com.example.UseMe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.UseMe.Constants.ServerResponseStatus;
import com.example.UseMe.Dto.CarOwnerSignUp;
import com.example.UseMe.Dto.CarOwnerUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Service.CarOwnerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/owner", produces = "application/json")
@Api(tags = " CarOwner Account Management", description = "Endpoint")
public class CarOwnerController {

	@Autowired
	CarOwnerService ownerService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	/*****************************************************************************************************************
	                                   CAR  OWNER ACCOUNT CREATION
	 *****************************************************************************************************************/
		
		
		@ApiOperation(value = "Register a car owner account", response = ServerResponse.class)
		@RequestMapping(value = "/create{userCode}", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> create(@RequestHeader("Authorization")  String authorization, @PathVariable("userCode") String userCode,  @RequestBody CarOwnerSignUp ownerSignUp){
			
			
			ServerResponse response = new ServerResponse();
			
			
			try {
				response = ownerService.createCarOwner(userCode, ownerSignUp);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}

	/*******************************************************************************************************************
	 * 	                           UPDATE CAR OWNER ACCOUNT DETAILS
	 *******************************************************************************************************************/
		@ApiOperation(value = "Update a car owner account", response = ServerResponse.class)
		@RequestMapping(value = "/update/{ownerId}", method = RequestMethod.PUT)
		@ResponseBody
	    public ResponseEntity<?> update(@RequestHeader("Authorization")  String authorization,  @PathVariable("ownerId")  String ownerId, @RequestBody CarOwnerUpdate ownerUpdate){
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = ownerService.updateCarOwner(ownerId, ownerUpdate);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
		
	
/****************************************************************************************************************
 * 		                         GET CAR OWNER BY PHONE NUMBER
 ****************************************************************************************************************/
		
		@ApiOperation(value = "Get car owner by phone number", response = ServerResponse.class)
		@RequestMapping( value = "/phone-no{ownerPhoneNo}", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> viewByCarOwnerPhoneNo(@RequestHeader("Authorization")  String authorization,  @PathVariable("ownerPhoneNo") String ownerPhoneNo ){
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = ownerService.viewByOwnerPhoneNo(ownerPhoneNo);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
		
/****************************************************************************************************************
 * 		                            GET ALL CAR OWNERS
 ***************************************************************************************************************/
		
		@ApiOperation(value = "Get all car owners", response = ServerResponse.class)
		@RequestMapping(value = "/all", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> findAll(@RequestHeader("Authorization")  String authorization) {
		 
		 ServerResponse response = new ServerResponse();
			
			try {
				response = ownerService.viewAll();
								
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	         response.setStatus(ServerResponseStatus.FAILED);
	        
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/***************************************************************************************************************
 * 		                 DELETE CAR OWNER BY ID
 ***************************************************************************************************************/
		
		@ApiOperation(value = "Delete car owner account", response = ServerResponse.class)
		@RequestMapping(value = "/delete{ownerId}", method = RequestMethod.DELETE)
		@ResponseBody
		public ResponseEntity<?> delete(@RequestHeader("Authorization")  String authorization,  @RequestParam String ownerId){
			
			ServerResponse response = new ServerResponse();
			
			try {
				response = ownerService.deleteOwner(ownerId);
				
			} catch (Exception e) {
				response.setData("An error occured => " + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}	
		
}
