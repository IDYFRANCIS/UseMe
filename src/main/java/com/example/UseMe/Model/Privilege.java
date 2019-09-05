package com.example.UseMe.Model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.example.UseMe.Enum.UserPrivilageType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable{

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy ="uuid2")
    @Column(name = "privilege_id", nullable = false, unique = true)
	private String id;

	@Column(name = "name")
    private UserPrivilageType name;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "privileges") 
	private Collection<User> users;

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserPrivilageType getName() {
		return name;
	}

	public void setName(UserPrivilageType name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	} 
	
}
