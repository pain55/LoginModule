package com.servicemodules.LoginModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.servicemodules.LoginModule.exception.UserNotFoundException;
import com.servicemodules.LoginModule.model.JwtRequest;
import com.servicemodules.LoginModule.model.JwtResponse;
import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.service.impl.JwtUtil;
import com.servicemodules.LoginModule.service.impl.UserDetailsServiceImpl;
import com.servicemodules.LoginModule.service.impl.UserServiceImpl;

@RestController
@CrossOrigin
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken( @RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Username not found");
		}
		
//		User has been authenticated
		
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
		
	}
	
//	To get the current user details so we are getting details from spring security
	@GetMapping("/current-user")
	public UserDetails getCurrentUser() throws UserNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		System.out.println(userServiceImpl.getUser(username));
		
		return userDetailsServiceImpl.loadUserByUsername(username);
		
	}
	
	
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			throw new Exception("User Disabled!");
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Credentials "+e.getMessage());
		}
		
		
	}

}
