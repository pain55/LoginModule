package com.servicemodules.LoginModule.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User with username doesn't exist!!");
	}
}
