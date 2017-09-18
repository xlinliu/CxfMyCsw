package com.csw.utils.custometypes;

/**
 * 传感器通信能力属性
 * 
 * @author yxliang
 * 
 */
public class SensorCommunicationProValueType {
	private String sensorid;//
	private String proname;
	private Object provalue;

	/**
	 * 获取传感器标识符
	 * 
	 * @return
	 */
	public String getSensorid() {
		return sensorid;
	}

	/**
	 * 设置传感器标识符
	 * 
	 */
	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	/**
	 * 获取传感器通信属性名称
	 * 
	 * @return
	 */
	public String getProname() {
		return proname;
	}

	/**
	 * 设置传感器通信属性名
	 * 
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}

	/**
	 * 获取传感器通信属性值
	 * 
	 * @return
	 */
	public Object getProvalue() {
		return provalue;
	}

	/**
	 * 设置传感器通信属性值
	 * 
	 */
	public void setProvalue(Object provalue) {
		this.provalue = provalue;
	}

}
