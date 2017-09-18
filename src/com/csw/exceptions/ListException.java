package com.csw.exceptions;

public class ListException extends Exception {

	private static final long serialVersionUID = 1735077341345790487L;

	public ListException() {
	}

	public ListException(String message) {
		super(message);
	}

	public ListException(Throwable cause) {
		super(cause);
	}

	public ListException(String message, Throwable cause) {
		super(message, cause);
	}
}
