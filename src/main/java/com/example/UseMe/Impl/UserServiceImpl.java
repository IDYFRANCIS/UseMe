package com.example.UseMe.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.mail.Address;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UseMe.Constants.AppConstants;
import com.example.UseMe.Constants.ServerResponseStatus;
import com.example.UseMe.Dto.ActivateUserRequest;
import com.example.UseMe.Dto.PasswordRestDto;
import com.example.UseMe.Dto.ResendUserActivationCodeDto;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Dto.SignInRequest;
import com.example.UseMe.Dto.SignUpRequest;
import com.example.UseMe.Enum.UserPrivilageType;
import com.example.UseMe.Enum.UserRoleType;
import com.example.UseMe.Model.Privilege;
import com.example.UseMe.Model.User;
import com.example.UseMe.Repository.PrivilegeRepository;
import com.example.UseMe.Repository.UserRepository;
import com.example.UseMe.Service.UserService;
import com.example.UseMe.utility.Utility;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	AppConstants appConstants;
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	
	
    Utility utility = new Utility();
	
	 private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public Collection<User> findAll(){
		try {
			return (Collection<User>) userRepository.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public User findById(String id){
		
		try {
			return userRepository.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByEmail(String email){
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByPhone(String phone){
		try {
			return userRepository.findByPhone(phone);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public User findByPhoneOrEmail(String emailOrphone){
		try {
			return userRepository.findByPhoneOrEmail(emailOrphone, emailOrphone);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public ServerResponse create(SignUpRequest request){
		ServerResponse response = new ServerResponse();
		
		User User = null;
		
		String email = request.getEmail() != null ? request.getEmail() : request.getEmail();
		String phone = request.getPhone() != null ? request.getPhone() : request.getPhone();;
		String firstname = request.getFirstname() != null ? request.getFirstname() : request.getFirstname();
		String lastname = request.getLastname() != null ? request.getLastname() : request.getLastname();

		if (email == null || !Utility.isValidEmail(email)) {
			
			response.setData("");
            response.setMessage("Please enter valid email address");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);
            return response;
		}
		
		if (phone == null || !Utility.isValidPhone(phone)) {
			response.setData("");
            response.setMessage("Please enter valid phone number");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		
		if (firstname == null || firstname.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter firstname");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		if (lastname == null || lastname.isEmpty()) {
			response.setData("");
            response.setMessage("Please enter lastname");
            response.setSuccess(false);
            response.setStatus(ServerResponseStatus.FAILED);

            return response;
		}
		
		try {
			
			User requestUser = userRepository.findByPhoneOrEmail(phone, email);
			
			if (requestUser != null) {
				response.setData("");
                response.setMessage("User email or number already exist");
                response.setSuccess(false);
                response.setStatus(ServerResponseStatus.FAILED);

                return response;
			}
			
			Privilege isAdmin = privilegeRepository.findByName(UserPrivilageType.isAdmin);
			Privilege owner = privilegeRepository.findByName(UserPrivilageType.owner);
			Privilege renter = privilegeRepository.findByName(UserPrivilageType.renter);
			Privilege user = privilegeRepository.findByName(UserPrivilageType.user);
			
			
			Collection<Privilege> adminPrivileges = new HashSet<>();
			adminPrivileges.add(isAdmin);
			adminPrivileges.add(owner);
			adminPrivileges.add(renter);
			adminPrivileges.add(user);
			
			
			User = new User();
			User.setPrivileges(adminPrivileges);
			String activationCode =  Utility.generateRandomString(40);

			User.setRole(UserRoleType.USER);
			User.setEmail(email);
			User.setFirstname(firstname);
			User.setLastname(lastname);
			User.setPhone(phone);
			User.setDateCreated(new Date());
			User.setActive(false);
			User.setActivationCode(activationCode);
			User.setUserCode("U" + System.currentTimeMillis());
		
			entityManager.persist(User);
			
			response.setData(User);
	        response.setMessage("User successfully created");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);

		} catch (Exception e) {
		  response.setData("");
          response.setMessage("Failed to create user User");
          response.setSuccess(false);
          response.setStatus(ServerResponseStatus.FAILED);

          logger.error("An error occured while creating recipient User");
          e.printStackTrace();
		}
		return response;
		
	}
	
	@Override
	public ServerResponse userActivation(ActivateUserRequest request){
		ServerResponse response = new ServerResponse();
		
		try {
			
			String activationCode = request.getActivationCode() != null ? request.getActivationCode() : request.getActivationCode();
			String password = request.getPassword() != null ? request.getPassword() : request.getPassword();
			
			User UserCode = userRepository.findByActivationCode(activationCode);

			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("Invalid activation Code");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}

			User User = entityManager.find(User.class, UserCode.getId());
			User.setPassword(passwordEncoder.encode(password));
			User.setActive(true);
			User.setActivationCode(null);
						           
			
			response.setData("User successfully activated");
	        response.setMessage("User successfully activated");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to create user User");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse reSendUserActivation(ResendUserActivationCodeDto email){
		ServerResponse response = new ServerResponse();
		
		try {
			
			User UserCode = findByPhoneOrEmail(email.getEmail());
			
			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
		
			String activationCode = Utility.generateRandomString(40);

			User User = entityManager.find(User.class, UserCode.getId());
			User.setActivationCode(activationCode);
			
			response.setData("");
	        response.setMessage("Activation code sent successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to create user User");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse reSendUserPassword(ResendUserActivationCodeDto email){
		ServerResponse response = new ServerResponse();
		
		try {

			User UserCode = findByPhoneOrEmail(email.getEmail());
			
			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
	        
			String passwordResetCode =  Utility.generateRandomString(40);

			User User = entityManager.find(User.class, UserCode.getId());
			User.setActivationCode(passwordResetCode);
			
			response.setData("password reset code sent successfully");
	        response.setMessage("password reset code sent successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to resend User Password");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse passwordReset(PasswordRestDto request){
		ServerResponse response = new ServerResponse();
		
		try {
			String passwordResetCode = request.getResetCode() != null ? request.getResetCode() : request.getResetCode();
			String password = request.getPassword() != null ? request.getPassword() : request.getPassword();

			User UserCode = userRepository.findByActivationCode(passwordResetCode);

			if (UserCode == null) {
				
				response.setData("");
		        response.setMessage("Invalid activation Code");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
				
				return response;
			}
			
			User User = entityManager.find(User.class, UserCode.getId());
			User.setPassword(passwordEncoder.encode(password));
			User.setActive(true);
			User.setActivationCode(null);
			
			response.setData("");
	        response.setMessage("User password successfully changed");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
			
		} catch (Exception e) {
			
			response.setData("");
	        response.setMessage("Failed to reset User Password");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	          
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ServerResponse login(SignInRequest request){
		
		ServerResponse response = new ServerResponse();
		try {
			
			logger.info(request.getUsername());
			
			//convert client id and client secret to base64 token 
			String authorization = Utility.getCredentials(appConstants.CLIENT_ID, appConstants.CLIENT_SECRET);
			logger.info(authorization);
			request.setGrant_type(appConstants.GRANT_TYPE);
			
			
			System.out.println("this is to check for request" + request);
			
			//send login request
			response = Utility.loginHttpRequest2( appConstants.APP_LOGIN_URL, request, authorization);
			
		} catch (Exception e) {
			response.setData("Something went wrong !!!");
 			response.setMessage("User authentication failed");
 			response.setSuccess(false);
			response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
			
			return response;
		}
		
		return response;
	}


	@Override
	public ServerResponse getAllUsers() {
		ServerResponse response = new ServerResponse();
		try {
			
			Collection<User> Users = findAll();
			
			if (Users.size() < 1) {
				response.setData(Users);
		        response.setMessage("User list is empty");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.OK);
		        return response;
			}
			
			response.setData(Users);
	        response.setMessage("Get data successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	@Override
	public ServerResponse getUserByUsername(String userCode) {
		ServerResponse response = new ServerResponse();
		
		if (userCode == null || userCode.isEmpty()) {
			response.setData("");
	        response.setMessage("User list is empty");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.FAILED);
	        return response;
		}
		
		try {
			
			User User = userRepository.findByUserCode(userCode);
			
			if (User == null) {
				response.setData("");
		        response.setMessage("User not found");
		        response.setSuccess(false);
		        response.setStatus(ServerResponseStatus.FAILED);
		        return response;
			}
			
			response.setData(User);
	        response.setMessage("Get data successfully");
	        response.setSuccess(true);
	        response.setStatus(ServerResponseStatus.OK);
		} catch (Exception e) {
			response.setData("");
	        response.setMessage("Something went wrong");
	        response.setSuccess(false);
	        response.setStatus(ServerResponseStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	

	
}
