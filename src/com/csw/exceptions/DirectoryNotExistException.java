package com.csw.exceptions;

public class DirectoryNotExistException extends Exception {
	private static final long serialVersionUID = 115873384978282723L;

	public DirectoryNotExistException() {
		super();
	}

	public DirectoryNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public DirectoryNotExistException(String message) {
		super(message);
	}

	public DirectoryNotExistException(Throwable cause) {
		super(cause);
	}

}
