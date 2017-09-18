package com.csw.exceptions;

public class TransactionProcessException extends Exception {
	private static final long serialVersionUID = 1L;

	public TransactionProcessException() {
		super();
	}

	public TransactionProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionProcessException(String message) {
		super(message);
	}

	public TransactionProcessException(Throwable cause) {
		super(cause);
	}

}
