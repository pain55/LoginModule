package com.servicemodules.LoginModule.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// this class is created to implement core spring security userdetails interface
// overrided necessary methods 
// created seperate class to make everything look clean instead of implementing it in user model

public class UserDetailsImpl implements UserDetails {

	private User user;

	public UserDetailsImpl(User user) {
		
		this.user = user;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Authority> authorities = new HashSet<>();
		
//		adding each userroles for user into spring security grantedauthority 
//		so that spring security knows the authorities of every user 
//		and authorize based on that
		this.user.getUserRoles().forEach(UserRole -> {
			authorities.add(new Authority(UserRole.getRole().getRoleName()));
		});
		return authorities;

	}

	

	public String getUsername() {
		return this.user.getUsername();
	}


	public String getPassword() {
		return this.user.getPassword();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
