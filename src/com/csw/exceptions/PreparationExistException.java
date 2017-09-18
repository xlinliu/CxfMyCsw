package com.csw.exceptions;

public class PreparationExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public PreparationExistException() {
	}

	public PreparationExistException(String message) {
		super(message);
	}

	public PreparationExistException(Throwable cause) {
		super(cause);
	}

	public PreparationExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
