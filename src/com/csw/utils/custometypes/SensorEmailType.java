package com.csw.utils.custometypes;

public class SensorEmailType {
	private String sensorid;
	private String emailStr;

	/**
	 * 获取传感器的标识符
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
	 * 获取传感器负责人的联系的email地址
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
