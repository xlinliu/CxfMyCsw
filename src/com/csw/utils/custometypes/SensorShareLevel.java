/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午03:08:40
 */
public class SensorShareLevel {
	private String sensorid;// 传感器的标识符
	private String sharelevel;// 传感器的分享级别
	private Boolean isShare;// 传感器是否可共享
	private String sensorowner;// 传感器的所有者

	/**
	 * @return the sensorowner
	 */
	public String getSensorowner() {
		return sensorowner;
	}

	/**
	 * @param sensorowner
	 *            the sensorowner to set
	 */
	public void setSensorowner(String sensorowner) {
		this.sensorowner = sensorowner;
	}

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getSharelevel() {
		return sharelevel;
	}

	public void setSharelevel(String sharelevel) {
		this.sharelevel = sharelevel;
	}

	public Boolean getIsShare() {
		return isShare;
	}

	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}

}
