/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-25 下午07:09:53
 */
public class PlatformAndSensorIdsForOracleType {
	private String PLATFORMID;// 传感器标识符
	private String SUBSENSORS;// 以逗号隔开

	/**
	 * @return the pLATFORMID
	 */
	public String getPLATFORMID() {
		return PLATFORMID;
	}

	/**
	 * @param pLATFORMID
	 *            the pLATFORMID to set
	 */
	public void setPLATFORMID(String pLATFORMID) {
		PLATFORMID = pLATFORMID;
	}

	/**
	 * @return the sUBSENSORS
	 */
	public String getSUBSENSORS() {
		return SUBSENSORS;
	}

	/**
	 * @param sUBSENSORS
	 *            the sUBSENSORS to set
	 */
	public void setSUBSENSORS(String sUBSENSORS) {
		SUBSENSORS = sUBSENSORS;
	}

}
