package com.flux.web.util.exception;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(String message){
		super(message);
	}
	
	public InvalidUserException(Throwable throwable){
		super(throwable);
	}
	
	public InvalidUserException(String message, Throwable throwable){
		super(message, throwable);
	}
}
