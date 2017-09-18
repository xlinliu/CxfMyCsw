package com.csw.exceptions;

public class FileExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileExistException() {
		super();
	}

	public FileExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileExistException(String message) {
		super(message);
	}

	public FileExistException(Throwable cause) {
		super(cause);
	}
}
