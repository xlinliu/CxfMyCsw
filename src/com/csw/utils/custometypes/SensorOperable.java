/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午02:55:48
 */
public class SensorOperable {
	private String sensorid;// 传感器标识符
	private Boolean isOperable;// 传感器是否可控

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public Boolean getIsOperable() {
		return isOperable;
	}

	public void setIsOperable(Boolean isOperable) {
		this.isOperable = isOperable;
	}
}
