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
import com.example.UseMe.Dto.CarSignUp;
import com.example.UseMe.Dto.CarUpdate;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Service.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/car", produces = "application/json")
@Api(tags = "Car Account Management", description = "Endpoint")
public class CarController {
	
	@Autowired
	CarService carService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
/*****************************************************************************************************************
                                   CAR ACCOUNT CREATION
 *****************************************************************************************************************/
	
	
	@ApiOperation(value = "Register car account", response = ServerResponse.class)
	@RequestMapping( value = "/create/{ownerId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestHeader("Authorization")  String authorization,  @RequestBody CarSignUp carSignUp, @PathVariable("ownerId") String ownerId){
		
		
		ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.createCar(ownerId, carSignUp);
		
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

/*******************************************************************************************************************
 * 	                           UPDATE CAR ACCOUNT DETAILS
 *******************************************************************************************************************/
	@ApiOperation(value = "Update car account", response = ServerResponse.class)
	@RequestMapping(value = "/update/{carId}", method = RequestMethod.PUT)
	@ResponseBody
    public ResponseEntity<?> update(@RequestHeader("Authorization")  String authorization,  @PathVariable("carId") String carId, @RequestBody CarUpdate carUpdate){
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {

			response = carService.updateCar(carId, carUpdate);
			
			
		} catch (Exception e) {
	     response.setData("An error occured" + e.getMessage());
	     response.setMessage("An error occured");
	     response.setSuccess(false);
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
/********************************************************************************************************************
 * 	                           GET CAR BY BRAND
 *******************************************************************************************************************/
	
	@ApiOperation(value = "Get car by brand", response = ServerResponse.class)
	@RequestMapping( value = "/carbrand{carBrand}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> viewByCarBrand(@RequestHeader("Authorization")  String authorization,    @PathVariable("carBrand") String carBrand ){
	 
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.viewByBrand(carBrand);
			
			
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
 	
	
/*************************************************************************************************************
 * 	                            GET CAR BY REG NUMBER
 ************************************************************************************************************/
	

	@ApiOperation(value = "Get car by reg no", response = ServerResponse.class)
	@RequestMapping( value = "/regno{carRegNo}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> viewByCarRegNo(@RequestHeader("Authorization")  String authorization,   @PathVariable("carRegNo") String carRegNo ){
	 
		
	 ServerResponse response = new ServerResponse();
			
		try {
			response = carService.viewByRegNo(carRegNo);
			
			
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
	
/*******************************************************************************************************************
 * 	                                 GET ALL CARS
 ******************************************************************************************************************/
	
	@ApiOperation(value = "Get all cars", response = ServerResponse.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> viewAll(@RequestHeader("Authorization")  String authorization) {
	 
	 ServerResponse response = new ServerResponse();
		
		try {
			response = carService.viewAll();
			
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
/****************************************************************************************************************
 * 	                      GET CAR BY CAR BRAND AND MAKE YEAR
 ***************************************************************************************************************/
	
	
	@ApiOperation(value = "Get car by brand and make year", response = ServerResponse.class)
	@RequestMapping(value = "/brand/{carBrand}/year/{carMakeYear}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> findAllByCarBrandAndMakeYear(@RequestHeader("Authorization")  String authorization,    @PathVariable("carBrand") String carBrand, @PathVariable("carMakeYear") String carMakeYear){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = carService.viewAllByCarBrandAndMakeYear(carBrand, carMakeYear);
			
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
/****************************************************************************************************************
 * 	                         DELETE CAR BY CAR ID
 ***************************************************************************************************************/
	
	@ApiOperation(value = "Delete car account", response = ServerResponse.class)
	@RequestMapping(value = "/delete{carId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestHeader("Authorization")  String authorization,   @RequestParam String carId){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = carService.delete(carId);
		
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
			response.setMessage("An error occured");
			response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
}
