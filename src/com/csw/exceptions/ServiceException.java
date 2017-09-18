package com.csw.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "ServiceException", faultBean = "com.csw.exceptions.ServiceFault")
public class ServiceException extends Exception {
	private static final long serialVersionUID = 8478032922274320285L;
	private ServiceFault details;

	public ServiceException(String msg) {
		super(msg);
		this.details = new ServiceFault();
	}

	public ServiceException(String msg, ServiceFault details) {
		super(msg);
		this.details = details;
	}

	public ServiceException(ServiceFault details) {
		this.details = details;
	}

	public ServiceFault getDetails() {
		return details;
	}

	public void setDetails(ServiceFault details) {
		this.details = details;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ServiceFault getFaultInfo() {
		return details;
	}
}
