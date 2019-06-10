package com.example.UseMe.Dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class ServerResponse {
	
	
		private Object data;

		private int status;
		
	    private boolean success;
	    
		private String message;
		
				
		public ServerResponse() {

		}

		public ServerResponse(int status, Object data, boolean success, String message){
			this.data = data;
			this.status = status;
			this.success = success;
			this.message = message;
		}
		
		public Object getData() {
			return data;
		}
		
		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public void setData(Object data) {
			this.data = data;
		}
		
		public int getStatus() {
			return status;
		}
		
		public void setStatus(int status) {
			this.status = status;
		}
		
		 public String getMessage() {
				return message;
	    }

	    public void setMessage(String message) {
				this.message = message;
			}
		
		
		@Override
	    public String toString() {
	        return getData().toString();
		}

		public static HttpStatus getStatus(int status){
			return HttpStatus.valueOf(status);
		}

		
	
}
