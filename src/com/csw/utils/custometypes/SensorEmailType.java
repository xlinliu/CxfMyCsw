package com.csw.utils.custometypes;

public class SensorEmailType {
	private String sensorid;
	private String emailStr;

	/**
	 * ��ȡ�������ı�ʶ��
	 * 
	 * @return
	 */
	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	/**
	 * ��ȡ�����������˵���ϵ��email��ַ
	 * 
	 * @return
	 */
	public String getEmailStr() {
		return emailStr;
	}

	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
	}

}
