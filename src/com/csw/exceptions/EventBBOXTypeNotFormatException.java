package com.csw.exceptions;

public class EventBBOXTypeNotFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventBBOXTypeNotFormatException() {
	}

	public EventBBOXTypeNotFormatException(String message) {
		super(message);
	}

	public EventBBOXTypeNotFormatException(Throwable cause) {
		super(cause);
	}

	public EventBBOXTypeNotFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
