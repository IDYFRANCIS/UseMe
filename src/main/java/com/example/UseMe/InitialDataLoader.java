package com.example.UseMe;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.UseMe.Constants.AppConstants;
import com.example.UseMe.Enum.UserPrivilageType;
import com.example.UseMe.Enum.UserRoleType;
import com.example.UseMe.Model.Privilege;
import com.example.UseMe.Model.User;
import com.example.UseMe.Repository.PrivilegeRepository;
import com.example.UseMe.Repository.UserRepository;


@Component
@Transactional
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AppConstants appConstants;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	private boolean hasBeenSetup;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private static Logger logger = LogManager.getLogger(InitialDataLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (hasBeenSetup) {
			return;
		}

		createPrivilagesIfNotFound();
		createAdminAccountIfNotFound();
		
		
		hasBeenSetup = true;
	}

	private Privilege createPrivilagesIfNotFound() {

		Privilege privileges;
		
		Privilege isAdmin = privilegeRepository.findByName(UserPrivilageType.isAdmin);
		Privilege owner = privilegeRepository.findByName(UserPrivilageType.owner);
		Privilege renter = privilegeRepository.findByName(UserPrivilageType.renter);
		Privilege user = privilegeRepository.findByName(UserPrivilageType.user);
		Privilege create = privilegeRepository.findByName(UserPrivilageType.create);
		Privilege read = privilegeRepository.findByName(UserPrivilageType.read);
		Privilege update = privilegeRepository.findByName(UserPrivilageType.update);
		Privilege delete = privilegeRepository.findByName(UserPrivilageType.delete);

		
		
		if (isAdmin == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.isAdmin);
			privilegeRepository.save(privileges);
		}

		if (owner == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.owner);
			privilegeRepository.save(privileges);
		}
		if (renter == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.renter);
			privilegeRepository.save(privileges);
		}

		if (user == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.user);
			privilegeRepository.save(privileges);
		}
		
		if (create == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.create);
			privilegeRepository.save(privileges);
		}
		
		if (read == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.read);
			privilegeRepository.save(privileges);
		}

		if (update == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.update);
			privilegeRepository.save(privileges);
		}
		
		if (delete == null) {
			privileges = new Privilege();
			privileges.setName(UserPrivilageType.delete);
			privilegeRepository.save(privileges);
		}
	
		return null;
	}

	
	private User createAdminAccountIfNotFound() {

		User userAccount = userRepository.findByEmail(appConstants.APP_ADMIN_EMAIL);
		Privilege create = privilegeRepository.findByName(UserPrivilageType.create);
		Privilege read = privilegeRepository.findByName(UserPrivilageType.read);
		Privilege update = privilegeRepository.findByName(UserPrivilageType.update);
		Privilege delete = privilegeRepository.findByName(UserPrivilageType.delete);
		Privilege isAdmin = privilegeRepository.findByName(UserPrivilageType.isAdmin);
		
		
		Collection<Privilege> adminPrivileges = new HashSet<>();
		adminPrivileges.add(create);
		adminPrivileges.add(read);
		adminPrivileges.add(update);
		adminPrivileges.add(delete);
		adminPrivileges.add(isAdmin);
		
		
		logger.info("Starting to create admin account ");

		if (userAccount != null) {
			return null;
		}

		User user = new User();
		user.setActive(true);
		user.setEmail(appConstants.APP_ADMIN_EMAIL);
		user.setPhone(appConstants.APP_DEFAULT_ADMIN_PHONE);
		user.setFirstname(appConstants.APP_DEFAULT_ADMIN_NAME);
		user.setLastname(appConstants.APP_DEFAULT_ADMIN_NAME);
		user.setPassword(passwordEncoder.encode(appConstants.APP_ADMIN_PASSWORD));
		user.setRole(UserRoleType.SYSTEM_ADMIN);
		user.setPrivileges(adminPrivileges);

		logger.info("Admin Account " + user.getEmail());
		
		User findEmail = userRepository.findByEmail(user.getEmail());

		if (findEmail != null) {
			return null;
		}

		userRepository.save(user);

		return null;
	}

}
