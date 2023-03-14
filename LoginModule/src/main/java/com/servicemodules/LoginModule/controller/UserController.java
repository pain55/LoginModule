package com.servicemodules.LoginModule.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicemodules.LoginModule.exception.UserFoundException;
import com.servicemodules.LoginModule.exception.UserNotFoundException;
import com.servicemodules.LoginModule.model.Role;
import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.model.UserRole;
import com.servicemodules.LoginModule.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/")
	public User registerUser(@RequestBody User user) throws UserFoundException{
		Role role = new Role(3L,"ROLE_NORMAL");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserRole userRole = new UserRole(user,role);
		Set<UserRole> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);
		userService.createUser(user, userRoleSet);
		
		return user;
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) throws UserNotFoundException {
		return this.userService.getUser(username);
	}
	
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable long userId) throws UserNotFoundException {
		this.userService.deleteUser(userId);
	}
}
