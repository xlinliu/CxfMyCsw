package com.csw.exceptions;

public class MetadataInfoException extends Exception {
	private static final long serialVersionUID = -3838426643134348318L;

	public MetadataInfoException() {
	}

	public MetadataInfoException(String message) {
		super(message);
	}

	public MetadataInfoException(Throwable cause) {
		super(cause);
	}

	public MetadataInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetadataInfoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
