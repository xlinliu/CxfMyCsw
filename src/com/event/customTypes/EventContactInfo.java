package com.event.customTypes;

public class EventContactInfo {
	private String individualname;// 联系人信息
	private String organizationname;// 联系单位名称
	private String voice;// 电话号码
	private String facsimile;// 传真
	private String deliverypoint;// 详细地址
	private String city;// 城市
	private String administrativeArea;// 行政区域
	private String postalCode;// 邮政编码
	private String country;// 国家
	private String electronicMailAddress;// 电子邮件

	public String getIndividualname() {
		return individualname;
	}

	public void setIndividualname(String individualname) {
		this.individualname = individualname;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public String getFacsimile() {
		return facsimile;
	}

	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}

	public String getDeliverypoint() {
		return deliverypoint;
	}

	public void setDeliverypoint(String deliverypoint) {
		this.deliverypoint = deliverypoint;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getElectronicMailAddress() {
		return electronicMailAddress;
	}

	public void setElectronicMailAddress(String electronicMailAddress) {
		this.electronicMailAddress = electronicMailAddress;
	}
}
