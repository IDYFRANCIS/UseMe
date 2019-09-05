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
import com.example.UseMe.Dto.CarRenterSignUp;
import com.example.UseMe.Dto.CarRenterUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Service.CarRenterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/renter", produces = "application/json")
@Api(tags = "CarRenter Account Management", description = "Endpoint")
public class CarRenterController {

	@Autowired
	private CarRenterService renterService;
	
     private HttpHeaders responseHeaders = new HttpHeaders();
	
	/*****************************************************************************************************************
	                                   CAR RENTER ACCOUNT CREATION
	 *****************************************************************************************************************/
		
		
		@ApiOperation(value = "Register car renter account", response = ServerResponse.class)
		@RequestMapping(value = "/create{userCode}", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> create(@RequestHeader("Authorization")  String authorization, @PathVariable("userCode") String userCode, @RequestBody CarRenterSignUp renterSignUp){
			
			ServerResponse response = new ServerResponse();
			
			try {
				response = renterService.createCarRenter(userCode, renterSignUp);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}

	/*******************************************************************************************************************
	 * 	                           UPDATE CAR RENTER ACCOUNT DETAILS
	 *******************************************************************************************************************/
		@ApiOperation(value = "Update car renter account", response = ServerResponse.class)
		@RequestMapping(value = "/update{renterId}", method = RequestMethod.PUT)
		@ResponseBody
	    public ResponseEntity<?> update(@RequestHeader("Authorization")  String authorization,   @PathVariable("renterId") String renterId,  @RequestBody CarRenterUpdate renterUpdate){
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = renterService.updateCarRenter(renterId, renterUpdate);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/*****************************************************************************************************************
 * 		                  VIEW ALL CAR RENTERS
 ****************************************************************************************************************/
	
		@ApiOperation(value = "Get all car renters", response = ServerResponse.class)
		@RequestMapping(value = "/all", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> findAll(@RequestHeader("Authorization")  String authorization) {
		 
		 ServerResponse response = new ServerResponse();
			
			try {
				response = renterService.viewAll();
						
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	         response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
		
		
/*****************************************************************************************************************
 * 		                  VIEW ALL CAR RENTERS
****************************************************************************************************************/
		
		@ApiOperation(value = "Rent a car", response = ServerResponse.class)
		@RequestMapping(value = "/rent-a-car/{renterId}/{carId}", method = RequestMethod.PUT)
		@ResponseBody
		public ResponseEntity<?> rentCar(@RequestHeader("Authorization") String authorization, @PathVariable("renterId") String renterId, @PathVariable("carId") String carId) {
			
			ServerResponse response = new ServerResponse();
			
			try {
				
				response = renterService.rentCar(renterId, carId);
				
			} catch(Exception e) {
				
				response.setData("An error occured =>" + e.getMessage());
				response.setMessage("Failed to rent car");
				response.setSuccess(false);
				response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		
		}
		
/****************************************************************************************************************
 * 		                       VIEW ALL CAR RENTERS BY PHONE NUMBER
 ****************************************************************************************************************/
		
		@ApiOperation(value = "Get car renter by phone number", response = ServerResponse.class)
		@RequestMapping( value = "/phone-no{renterPhoneNo}", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> viewByRenterPhoneNo(@RequestHeader("Authorization")  String authorization,    @PathVariable("renterPhoneNo") String renterPhoneNo ){
		 	 
		 ServerResponse response = new ServerResponse();
				
			try {
				response = renterService.viewByRenterPhoneNo(renterPhoneNo);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/****************************************************************************************************************
 * 		                     DELETE CAR RENTER BY ID
 ***************************************************************************************************************/
		
		@ApiOperation(value = "Delete car renter account", response = ServerResponse.class)
		@RequestMapping(value = "/delete{renterId}", method = RequestMethod.DELETE)
		@ResponseBody
		public ResponseEntity<?> delete(@RequestHeader("Authorization")  String authorization,   @RequestParam String renterId){
			
			ServerResponse response = new ServerResponse();
			
			try {
				response = renterService.deleteRenter(renterId);
				
			} catch (Exception e) {
				response.setData("An error occured => " + e.getMessage());
				response.setMessage("An error occured");
				response.setSuccess(false);
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}
		
}
