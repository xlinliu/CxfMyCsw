package com.csw.exceptions;

public class DocumentnotExistException extends Exception {
	private static final long serialVersionUID = -4807364219517613443L;

	public DocumentnotExistException() {
		super();
	}

	public DocumentnotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentnotExistException(String message) {
		super(message);
	}

	public DocumentnotExistException(Throwable cause) {
		super(cause);
	}

}
