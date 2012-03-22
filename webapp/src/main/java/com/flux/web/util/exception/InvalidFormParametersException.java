package com.flux.web.util.exception;

public class InvalidFormParametersException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFormParametersException(String message) {
		super(message);
	}

	public InvalidFormParametersException(Throwable throwable) {
		super(throwable);
	}

	public InvalidFormParametersException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
