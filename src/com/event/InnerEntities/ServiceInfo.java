package com.event.InnerEntities;

public class ServiceInfo {
	private String servicename;
	private String servicetype;

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getServiceaddress() {
		return serviceaddress;
	}

	public void setServiceaddress(String serviceaddress) {
		this.serviceaddress = serviceaddress;
	}

	private String serviceaddress;
}
