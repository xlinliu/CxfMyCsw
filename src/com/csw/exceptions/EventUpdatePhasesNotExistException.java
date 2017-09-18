package com.csw.exceptions;

public class EventUpdatePhasesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventUpdatePhasesNotExistException() {
	}

	public EventUpdatePhasesNotExistException(String message) {
		super(message);
	}

	public EventUpdatePhasesNotExistException(Throwable cause) {
		super(cause);
	}

	public EventUpdatePhasesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
