package com.servicemodules.LoginModule.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicemodules.LoginModule.exception.UserFoundException;
import com.servicemodules.LoginModule.exception.UserNotFoundException;
import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.model.UserRole;
import com.servicemodules.LoginModule.repository.RoleRepository;
import com.servicemodules.LoginModule.repository.UserRepository;
import com.servicemodules.LoginModule.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	

	@Override
	public User createUser(User user,Set<UserRole> userRoles) throws UserFoundException {
		
//		checking whether the user already exist in database
		User localUser = this.userRepository.findByUsername(user.getUsername());
		
		if( localUser != null ) {
			System.out.println("User with the username already exist");
			throw new UserFoundException("User with given username already exist!! try with another username");
		}
		
		
//		if no user exist then we create new user in database
		
//		we are adding each role present in userRoles to role db
		for(UserRole userRole : userRoles) {
			roleRepository.save(userRole.getRole());
		}
		
//		we associating all userRoles to the user
		user.getUserRoles().addAll(userRoles);
		
//		saving user to user db
		localUser = this.userRepository.save(user);
		
		return localUser;
		
	}
	
	public User getUser(String username) throws UserNotFoundException{
		
		User local = this.userRepository.findByUsername(username);
		
		if(local == null) {
			System.out.println("Username doesn't exist");
			throw new UserNotFoundException();
		}
		
		return local;
	}

	
	public void deleteUser(long userId) throws UserNotFoundException{
		
		if(this.userRepository.findById(userId) == null) {
			System.out.println("User with the userId doesn't exist!!");
			throw new UserNotFoundException();
		}
		
		this.userRepository.deleteById(userId);
	}

	

}
