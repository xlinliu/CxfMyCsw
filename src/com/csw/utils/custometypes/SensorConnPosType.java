package com.csw.utils.custometypes;

/**
 * 描述传感器组织地址信息
 * 
 * @author yxliang
 * 
 */
public class SensorConnPosType {
	private String sensorid;
	private String posinfo;

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
	 * 获取传感器的组织单位信息
	 * 
	 * @return
	 */
	public String getPosinfo() {
		return posinfo;
	}

	public void setPosinfo(String posinfo) {
		this.posinfo = posinfo;
	}
}
