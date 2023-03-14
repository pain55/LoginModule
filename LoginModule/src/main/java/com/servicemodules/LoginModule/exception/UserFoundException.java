package com.servicemodules.LoginModule.exception;

@SuppressWarnings("serial")
public class UserFoundException extends Exception{
	public UserFoundException(String message) {
		super(message);
	}
}
