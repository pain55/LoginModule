package com.servicemodules.LoginModule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userRoleId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;
	
	
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}




	
	
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}




	public long getUserRoleId() {
		return userRoleId;
	}

	
}
