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
	
	
	@ApiOperation(value = "REGISTER A CAR ACCOUNT", response = ServerResponse.class)
	@RequestMapping( value = "/creatCar", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CarSignUp carSignUp){
		
		
		ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.createCar(carSignUp);
			response.setSuccess(true);
			response.setMessage("Car account created successfully");
			
			
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

/*******************************************************************************************************************
 * 	                           UPDATE CAR ACCOUNT DETAILS
 *******************************************************************************************************************/
	@ApiOperation(value = "UPDATE A CAR ACCOUNT", response = ServerResponse.class)
	@RequestMapping(value = "/updateCar/{carId}", method = RequestMethod.PUT)
	@ResponseBody
    public ResponseEntity<?> update(@PathVariable("carId") String carId, @RequestBody CarUpdate carUpdate){
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {

			response = carService.updateCar(carId, carUpdate);
			response.setSuccess(true);
			response.setMessage("Car account successfully updated");
		
		} catch (Exception e) {
	     response.setData("An error occured" + e.getMessage());
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
/********************************************************************************************************************
 * 	                           GET CAR BY BRAND
 *******************************************************************************************************************/
	
	@ApiOperation(value = "GET CAR BY BRAND", response = ServerResponse.class)
	@RequestMapping( value = "/byCarBrand{carBrand}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> viewByCarBrand(@PathVariable("carBrand") String carBrand ){
	 
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.viewByBrand(carBrand);
			response.setSuccess(true);
			response.setMessage("Car fetched by brand");
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
 	
	
/*************************************************************************************************************
 * 	                            GET CAR BY REG NUMBER
 ************************************************************************************************************/
	

	@ApiOperation(value = "GET CAR BY REG NO", response = ServerResponse.class)
	@RequestMapping( value = "/byCarRegNo{carRegNo}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> viewByCarRegNo(@PathVariable("carRegNo") String carRegNo ){
	 
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.viewByRegNo(carRegNo);
			response.setSuccess(true);
			response.setMessage("Car fetched successfully by Reg No");
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
	
/*******************************************************************************************************************
 * 	                                 GET ALL CARS
 ******************************************************************************************************************/
	
	@ApiOperation(value = "GET ALL CARS", response = ServerResponse.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<?> findAll() {
	 
	 
	 ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.viewAll();
			response.setSuccess(true);
			response.setMessage("All cars sucessfully fetched");
					
		
		} catch (Exception e) {
			response.setData("An error occured" + e.getMessage());
         response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	 
 }
	
/****************************************************************************************************************
 * 	                      GET CAR BY CAR BRAND AND MAKE YEAR
 ***************************************************************************************************************/
	
	
	@ApiOperation(value = "VIEW CAR BY BRAND AND MAKE YEAR", response = ServerResponse.class)
	@RequestMapping(value = "/brand&year/carBrand/carMakeYear", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> findAllByCarBrandAndMakeYear(@RequestParam String carBrand, @RequestParam String carMakeYear){
		
		ServerResponse response = new ServerResponse();
		
		
		try {
			response = carService.viewAllByCarBrandAndMakeYear(carBrand, carMakeYear);
			response.setSuccess(true);
			response.setMessage("Car fetched by brand and make year");
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
/****************************************************************************************************************
 * 	                         DELETE CAR BY CAR ID
 ***************************************************************************************************************/
	
	@ApiOperation(value = "DELETE CAR ACCOUNT", response = ServerResponse.class)
	@RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam String carId){
		
		ServerResponse response = new ServerResponse();
		
		try {
			response = carService.delete(carId);
			response.setSuccess(true);
			response.setMessage("Car successfully deleted");
			
		} catch (Exception e) {
			response.setData("An error occured => " + e.getMessage());
            response.setStatus(ServerResponseStatus.FAILED);
		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
	
	
}
