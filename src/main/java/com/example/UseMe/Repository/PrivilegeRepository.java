package com.example.UseMe.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.UseMe.Enum.UserPrivilageType;
import com.example.UseMe.Model.Privilege;


@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, String> {
	
	public Privilege findById(String id);

	public Privilege findByName(UserPrivilageType name);
	

}
