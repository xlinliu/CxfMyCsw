package com.csw.exceptions;

public class XMLnotFormatException extends Exception {
	private static final long serialVersionUID = 4563910232381477880L;

	public XMLnotFormatException() {
		super();
	}

	public XMLnotFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLnotFormatException(String message) {
		super(message);
	}

	public XMLnotFormatException(Throwable cause) {
		super(cause);
	}
}
