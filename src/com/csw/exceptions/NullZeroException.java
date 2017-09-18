package com.csw.exceptions;

public class NullZeroException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullZeroException() {
		super();
	}

	public NullZeroException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullZeroException(String message) {
		super(message);
	}

	public NullZeroException(Throwable cause) {
		super(cause);
	}

}
