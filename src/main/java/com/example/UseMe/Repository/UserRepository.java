package com.example.UseMe.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Model.User;


@Repository
public interface UserRepository extends CrudRepository<User,  String>{
	
	public User findById(String id);

	public User findByEmail(String email);
		
	public User findByUserCode(String userCode);
	
	public User findByPhone(String phone);
	
	public User findByPhoneOrEmail(String phone, String email);
		
	public User findByActivationCode(String activationCode);
	
	@Query("SELECT A FROM User A WHERE A.phone=:phone OR A.phone=:email")
	public User getUserByEmailOrPhone(@Param("phone")String phone, @Param("email")String email);
	

}
