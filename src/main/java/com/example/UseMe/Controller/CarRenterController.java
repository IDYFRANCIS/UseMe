package com.example.UseMe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
		
		
		@ApiOperation(value = "REGISTER CAR RENTER ACCOUNT", response = ServerResponse.class)
		@RequestMapping(value = "/createRenter", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> create(@RequestBody CarRenterSignUp renterSignUp){
			
			
			ServerResponse response = new ServerResponse();
			
			
			try {
				response = renterService.createCarRenter(renterSignUp);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}

	/*******************************************************************************************************************
	 * 	                           UPDATE CAR RENTER ACCOUNT DETAILS
	 *******************************************************************************************************************/
		@ApiOperation(value = "UPDATE CAR RENTER ACCOUNT", response = ServerResponse.class)
		@RequestMapping(value = "/updateRenter{renterId}", method = RequestMethod.PUT)
		@ResponseBody
	    public ResponseEntity<?> update(@PathVariable("renterId") String renterId,  @RequestBody CarRenterUpdate renterUpdate){
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = renterService.updateCarRenter(renterId, renterUpdate);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
	         response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/*****************************************************************************************************************
 * 		                  VIEW ALL CAR RENTERS
 ****************************************************************************************************************/
	
		@ApiOperation(value = "GET ALL CAR RENTER ACCOUNT", response = ServerResponse.class)
		@RequestMapping(value = "/all", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> findAll() {
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = renterService.viewAll();
						
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
	         response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/****************************************************************************************************************
 * 		                       VIEW ALL CAR RENTERS BY PHONE NUMBER
 ****************************************************************************************************************/
		
		@ApiOperation(value = "GET CAR RENTER BY PHONE NUMBER", response = ServerResponse.class)
		@RequestMapping( value = "/byRenterPhoneNo{renterPhoneNo}", method = RequestMethod.GET)
		@ResponseBody
	    public ResponseEntity<?> viewByRenterPhoneNo(@PathVariable("renterPhoneNo") String renterPhoneNo ){
		 
		 
		 ServerResponse response = new ServerResponse();
			
			
			try {
				response = renterService.viewByRenterPhoneNo(renterPhoneNo);
			
			} catch (Exception e) {
				response.setData("An error occured" + e.getMessage());
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		 
	 }
		
/****************************************************************************************************************
 * 		                     DELETE CAR RENTER BY ID
 ***************************************************************************************************************/
		
		@ApiOperation(value = "DELETE CAR RENTER ACCOUNT", response = ServerResponse.class)
		@RequestMapping(value = "/{renterId}", method = RequestMethod.DELETE)
		@ResponseBody
		public ResponseEntity<?> delete(@RequestParam String renterId){
			
			ServerResponse response = new ServerResponse();
			
			try {
				response = renterService.deleteRenter(renterId);
				
			} catch (Exception e) {
				response.setData("An error occured => " + e.getMessage());
	            response.setStatus(ServerResponseStatus.FAILED);
			}
			
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}
		
}
