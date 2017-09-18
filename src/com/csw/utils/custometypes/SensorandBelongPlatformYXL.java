package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述：传感器和其所属的平台的基本的信息 创建人:Administrator 创建时间: 2013-8-23
 * 下午01:43:25
 */
public class SensorandBelongPlatformYXL {
	private String sensor;// 获取传感器标识符
	private String platform;// 获取平台的标识符

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
}
