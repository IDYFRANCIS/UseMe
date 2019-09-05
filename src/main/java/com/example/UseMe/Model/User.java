package com.example.UseMe.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.UseMe.Enum.UserRoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable{
	
		
		@Id
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(name = "uuid", strategy ="uuid2")
		@Column(name = "user_id", nullable = false, unique = true)
		private String id;
		
		@Column(name = "user_code", unique = true)
		private String userCode;
		
		@JsonIgnore
		@Column(name = "password")
	    private String password;
		
		@Column(name = "firstname")
	    private String firstname;
		
		@Column(name = "lastname")
	    private String lastname;
		
		@Column(name = "middle_name")
		private String middleName;

	    @Column(name = "email", unique = true)
	    private String email;

	    @Column(name = "phone", unique = true)
	    private String phone;

	    @Column(name = "activation_code", unique = true)
	    private String activationCode;
	    
	    @Column(name = "active")
	    private boolean active;
	    
		@Column(name = "date_created", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date dateCreated;
		
		@Column(name = "role")
		private UserRoleType role;
		
		
		@JsonIgnore
		@LazyCollection(LazyCollectionOption.FALSE)
		@ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "user_privileges", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id"))
		private Collection<Privilege> privileges;


		

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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

		public Date getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}

		public UserRoleType getRole() {
			return role;
		}

		public void setRole(UserRoleType role) {
			this.role = role;
		}

		public Collection<Privilege> getPrivileges() {
			return privileges;
		}
		
		public void setPrivileges(Collection<Privilege> privileges) {
			this.privileges = privileges;
		}

}
