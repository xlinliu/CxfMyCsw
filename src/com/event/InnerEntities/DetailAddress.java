package com.event.InnerEntities;

public class DetailAddress {
	private String country;// �����Ĺ���
	private String province;// �������ڵ�ʡ��
	private String city;// �������ڵĳ���
	private String administrativeArea;// �¼������ľ��帺�����������
	private String deliveryPoint;// �¼�������

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
