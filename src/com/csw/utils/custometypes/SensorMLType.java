/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.Date;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-16 下午05:18:05
 */
public class SensorMLType {

	private String sensorid;// 传感器标识符
	private String owner;// 传感器所有者
	private Date savetime;// 保存时间

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

}
