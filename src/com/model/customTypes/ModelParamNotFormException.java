package com.model.customTypes;

public class ModelParamNotFormException extends Exception {
	private static final long serialVersionUID = 3567226591383793730L;

	public ModelParamNotFormException() {
		super();
	}

	public ModelParamNotFormException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModelParamNotFormException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelParamNotFormException(String message) {
		super(message);
	}

	public ModelParamNotFormException(Throwable cause) {
		super(cause);
	}
	
}
