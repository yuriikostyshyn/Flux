package com.flux.web.util.exception;

public class InvalidFormParametersException extends RuntimeException {

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
