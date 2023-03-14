package com.servicemodules.LoginModule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.model.UserDetailsImpl;
import com.servicemodules.LoginModule.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		
		
		if(user == null) {
			System.out.println("Username not found");
			throw new UsernameNotFoundException("user not found with username->"+username);
		}
	
		
		return new UserDetailsImpl(user);
	}
	
}
