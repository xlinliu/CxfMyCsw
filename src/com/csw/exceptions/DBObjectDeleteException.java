package com.csw.exceptions;

public class DBObjectDeleteException extends Exception {
	private static final long serialVersionUID = 1L;

	public DBObjectDeleteException() {
	}

	public DBObjectDeleteException(String message) {
		super(message);
	}

	public DBObjectDeleteException(Throwable cause) {
		super(cause);
	}

	public DBObjectDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

}
