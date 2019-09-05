package com.example.UseMe.Dto;

import java.util.Date;

import com.example.UseMe.Enum.UserRoleType;


public class UserDto {

	private String id;
	private String userCode = "";	
    private String firstname = "";
    private String lastname = "";
    private String middleName = "";
    private String email= "" ;
    private String phone = "";
    private String activationCode = "";
    private boolean active;
	private UserRoleType role;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public UserRoleType getRole() {
		return role;
	}
	public void setRole(UserRoleType role) {
		this.role = role;
	}
	
	
	
}
