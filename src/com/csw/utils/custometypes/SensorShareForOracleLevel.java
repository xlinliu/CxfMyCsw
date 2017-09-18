/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-27 下午07:56:18
 */
public class SensorShareForOracleLevel {
	private String SENSORID;// 传感器标识符
	private String SENSORSHARELEVEL;// 传感器共享级别
	private String SENSOROWNER;// 传感器所有者

	public String getSENSORID() {
		return SENSORID;
	}

	public void setSENSORID(String sENSORID) {
		SENSORID = sENSORID;
	}

	public String getSENSORSHARELEVEL() {
		return SENSORSHARELEVEL;
	}

	public void setSENSORSHARELEVEL(String sENSORSHARELEVEL) {
		SENSORSHARELEVEL = sENSORSHARELEVEL;
	}

	/**
	 * @return the sENSOROWNER
	 */
	public String getSENSOROWNER() {
		return SENSOROWNER;
	}

	/**
	 * @param sENSOROWNER
	 *            the sENSOROWNER to set
	 */
	public void setSENSOROWNER(String sENSOROWNER) {
		SENSOROWNER = sENSOROWNER;
	}

}
