/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-8-23 上午10:32:18
 */
public class SensorInfo {
	/**
	 * 传感器的标识符
	 */
	private String sensor;
	/**
	 * 传感器的类型
	 */
	private String sensortype;
	/**
	 * 传感器的全称
	 */
	private String sensorname;
	/**
	 * 传感器观测数据服务地址
	 */
	private String sensorsosurl;
	/**
	 * 传感器观测数据服务地址的机构号
	 */
	private String sensoroffering;

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

	public String getSensorsosurl() {
		return sensorsosurl;
	}

	public void setSensorsosurl(String sensorsosurl) {
		this.sensorsosurl = sensorsosurl;
	}

	public String getSensoroffering() {
		return sensoroffering;
	}

	public void setSensoroffering(String sensoroffering) {
		this.sensoroffering = sensoroffering;
	}
}
