package com.csw.exceptions;

public class EventNotExistsException extends Exception {

	private static final long serialVersionUID = -5446409869866005637L;

	public EventNotExistsException() {
	}

	public EventNotExistsException(String message) {
		super(message);
	}

	public EventNotExistsException(Throwable cause) {
		super(cause);
	}

	public EventNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
