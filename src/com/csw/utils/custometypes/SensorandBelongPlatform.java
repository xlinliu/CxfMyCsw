package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述：传感器和其所属的平台的基本的信息 创建人:Administrator 创建时间: 2013-8-23
 * 下午01:43:25
 */
public class SensorandBelongPlatform {
	private String sensor;// 获取传感器标识符
	private String platform;// 获取平台的标识符
	private String platformtype;// 获取平台的类型
	private String sensortype;// 获取传感器类型
	private String platformname;// 获取平台的名称
	private String sensorname;// 获取传感器的名称

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(String platformtype) {
		this.platformtype = platformtype;
	}

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

}
