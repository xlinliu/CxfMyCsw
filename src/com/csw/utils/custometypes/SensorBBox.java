/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-14 下午01:11:44
 */
public class SensorBBox {
	private String sensorid;// 传感器标识符
	private String bbox;// 对应的传感器的bbox

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getBbox() {
		return bbox;
	}

	public void setBbox(String bbox) {
		this.bbox = bbox;
	}

}
