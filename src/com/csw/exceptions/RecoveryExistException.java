package com.csw.exceptions;

public class RecoveryExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecoveryExistException() {
	}

	public RecoveryExistException(String message) {
		super(message);
	}

	public RecoveryExistException(Throwable cause) {
		super(cause);
	}

	public RecoveryExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
