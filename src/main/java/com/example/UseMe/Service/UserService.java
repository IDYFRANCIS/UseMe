package com.example.UseMe.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.UseMe.Dto.ActivateUserRequest;
import com.example.UseMe.Dto.PasswordRestDto;
import com.example.UseMe.Dto.ResendUserActivationCodeDto;
import com.example.UseMe.Dto.ServerResponse;
import com.example.UseMe.Dto.SignInRequest;
import com.example.UseMe.Dto.SignUpRequest;
import com.example.UseMe.Model.User;

@Service
public interface UserService {

	
	public Collection<User> findAll();
	
	public User findById(String id);
	
	public User findByEmail(String email);
	
	public User findByPhone(String phone);
	
	public User findByPhoneOrEmail(String emailOrphone);
	
	public ServerResponse create(SignUpRequest request);
	
	public ServerResponse userActivation(ActivateUserRequest request);
	
	public ServerResponse reSendUserActivation(ResendUserActivationCodeDto email);
	
	public ServerResponse reSendUserPassword(ResendUserActivationCodeDto email);
	
	public ServerResponse passwordReset(PasswordRestDto request);
	
	public ServerResponse login(SignInRequest request);
	
	public ServerResponse getAllUsers();
	
	public ServerResponse getUserByUsername(String username);
	
		
}
