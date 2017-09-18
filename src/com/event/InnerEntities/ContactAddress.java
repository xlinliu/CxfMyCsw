package com.event.InnerEntities;

public class ContactAddress {
	private String deliveryPoint;
	private String city;
	private String administrativeArea;
	private String postalCode;
	private String electronicMailAddress;
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeliveryPoint() {
		return deliveryPoint;
	}

	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getElectronicMailAddress() {
		return electronicMailAddress;
	}

	public void setElectronicMailAddress(String electronicMailAddress) {
		this.electronicMailAddress = electronicMailAddress;
	}
}
