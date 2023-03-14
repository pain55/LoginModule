package com.servicemodules.LoginModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicemodules.LoginModule.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
}
