package com.event.InnerEntities;

public class DetailAddress {
	private String country;// 发生的国家
	private String province;// 发生所在的省份
	private String city;// 发生所在的城市
	private String administrativeArea;// 事件发生的具体负责的行政区划
	private String deliveryPoint;// 事件发生点

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getDeliveryPoint() {
		return deliveryPoint;
	}

	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

}
