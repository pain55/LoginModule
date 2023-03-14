package com.servicemodules.LoginModule.service;

import java.util.Set;

import com.servicemodules.LoginModule.exception.UserFoundException;
import com.servicemodules.LoginModule.exception.UserNotFoundException;
import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.model.UserRole;

public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException;
	
	
	public User getUser(String username) throws UserNotFoundException;
	
	public void deleteUser(long userId) throws UserNotFoundException;
}
