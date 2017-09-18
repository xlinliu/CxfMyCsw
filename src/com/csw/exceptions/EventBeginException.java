package com.csw.exceptions;

public class EventBeginException extends Exception {
	private static final long serialVersionUID = -4431860045327282200L;

	public EventBeginException() {
		super();
	}

	public EventBeginException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventBeginException(String message) {
		super(message);
	}

	public EventBeginException(Throwable cause) {
		super(cause);
	}

}
