package com.csw.utils.custometypes;

public class SensorGeoType {
	private String sensorid;
	private String geoStr;

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
	 * 获取传感器的地址信息
	 * 
	 * @return
	 */
	public String getGeoStr() {
		return geoStr;
	}

	public void setGeoStr(String geoStr) {
		this.geoStr = geoStr;
	}

}
