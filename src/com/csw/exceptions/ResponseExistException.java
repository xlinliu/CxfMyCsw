package com.csw.exceptions;

public class ResponseExistException extends Exception {
	public ResponseExistException() {
		super();
	}

	public ResponseExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseExistException(String message) {
		super(message);
	}

	public ResponseExistException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 2083951266656809852L;

}
