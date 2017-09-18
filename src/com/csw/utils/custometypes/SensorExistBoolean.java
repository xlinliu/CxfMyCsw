/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午09:28:43
 */
public class SensorExistBoolean {
	private String sensorid;// 传感器标识符
	private Boolean isExist;// 是否存在
	private String owner;// 传感器所有者

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
