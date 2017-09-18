package com.csw.exceptions;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ServiceFault")
@XmlType(name = "com.csw.exceptions.ServiceFault")
public class ServiceFault {
	private String t;

	public ServiceFault() {
	}

	public ServiceFault(String t) {
		this.t = t;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
