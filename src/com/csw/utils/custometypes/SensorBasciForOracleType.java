/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-25 下午04:16:07
 */
public class SensorBasciForOracleType implements java.lang.Cloneable {
	private String SENSORID;// 传感器标识符
	private String SENSORLONGNAME;// 传感器全称
	private String SENSORSHORTNAME;// 传感器简称
	private String SENSORTYPE;// 传感器类型
	private String SENSORINTENDAPP;// 传感器预期应用
	private String SENSORKEYWORD;// 传感器关键字
	private String SENSORBBOX;// 传感器观测范围
	private String SENSORPOS;// 传感器位置
	private String SENSORBEGINTIME;// 传感器开始工作时间
	private String SENSORENDTIME;// 传感器结束工作时间
	private String SENSORORGAN;// 传感器的组织信息
	private String SENSOROWNER;// 传感器所有者
	private String SENSOROPERABLE;// 传感器可控性
	private String SENSORPUBLIC;// 传感器公开性
	
	@Override
	public SensorBasciForOracleType clone() {
		SensorBasciForOracleType sbfot;
		try {
			sbfot = (SensorBasciForOracleType) super.clone();
			return sbfot;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

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

	/**
	 * @return the sENSOROPERABLE
	 */
	public String getSENSOROPERABLE() {
		return SENSOROPERABLE;
	}

	/**
	 * @param sENSOROPERABLE
	 *            the sENSOROPERABLE to set
	 */
	public void setSENSOROPERABLE(String sENSOROPERABLE) {
		SENSOROPERABLE = sENSOROPERABLE;
	}

	/**
	 * @return the sENSORPUBLIC
	 */
	public String getSENSORPUBLIC() {
		return SENSORPUBLIC;
	}

	/**
	 * @param sENSORPUBLIC
	 *            the sENSORPUBLIC to set
	 */
	public void setSENSORPUBLIC(String sENSORPUBLIC) {
		SENSORPUBLIC = sENSORPUBLIC;
	}

	/**
	 * @return the sENSORID
	 */
	public String getSENSORID() {
		return SENSORID;
	}

	/**
	 * @param sENSORID
	 *            the sENSORID to set
	 */
	public void setSENSORID(String sENSORID) {
		SENSORID = sENSORID;
	}

	/**
	 * @return the sENSORLONGNAME
	 */
	public String getSENSORLONGNAME() {
		return SENSORLONGNAME;
	}

	/**
	 * @param sENSORLONGNAME
	 *            the sENSORLONGNAME to set
	 */
	public void setSENSORLONGNAME(String sENSORLONGNAME) {
		SENSORLONGNAME = sENSORLONGNAME;
	}

	/**
	 * @return the sENSORSHORTNAME
	 */
	public String getSENSORSHORTNAME() {
		return SENSORSHORTNAME;
	}

	/**
	 * @param sENSORSHORTNAME
	 *            the sENSORSHORTNAME to set
	 */
	public void setSENSORSHORTNAME(String sENSORSHORTNAME) {
		SENSORSHORTNAME = sENSORSHORTNAME;
	}

	/**
	 * @return the sENSORTYPE
	 */
	public String getSENSORTYPE() {
		return SENSORTYPE;
	}

	/**
	 * @param sENSORTYPE
	 *            the sENSORTYPE to set
	 */
	public void setSENSORTYPE(String sENSORTYPE) {
		SENSORTYPE = sENSORTYPE;
	}

	/**
	 * @return the sENSORINTENDAPP
	 */
	public String getSENSORINTENDAPP() {
		return SENSORINTENDAPP;
	}

	/**
	 * @param sENSORINTENDAPP
	 *            the sENSORINTENDAPP to set
	 */
	public void setSENSORINTENDAPP(String sENSORINTENDAPP) {
		SENSORINTENDAPP = sENSORINTENDAPP;
	}

	/**
	 * @return the sENSORKEYWORD
	 */
	public String getSENSORKEYWORD() {
		return SENSORKEYWORD;
	}

	/**
	 * @param sENSORKEYWORD
	 *            the sENSORKEYWORD to set
	 */
	public void setSENSORKEYWORD(String sENSORKEYWORD) {
		SENSORKEYWORD = sENSORKEYWORD;
	}

	/**
	 * @return the sENSORBBOX
	 */
	public String getSENSORBBOX() {
		return SENSORBBOX;
	}

	/**
	 * @param sENSORBBOX
	 *            the sENSORBBOX to set
	 */
	public void setSENSORBBOX(String sENSORBBOX) {
		SENSORBBOX = sENSORBBOX;
	}

	/**
	 * @return the sENSORPOS
	 */
	public String getSENSORPOS() {
		return SENSORPOS;
	}

	/**
	 * @param sENSORPOS
	 *            the sENSORPOS to set
	 */
	public void setSENSORPOS(String sENSORPOS) {
		SENSORPOS = sENSORPOS;
	}

	/**
	 * @return the sENSORBEGINTIME
	 */
	public String getSENSORBEGINTIME() {
		return SENSORBEGINTIME;
	}

	/**
	 * @param sENSORBEGINTIME
	 *            the sENSORBEGINTIME to set
	 */
	public void setSENSORBEGINTIME(String sENSORBEGINTIME) {
		SENSORBEGINTIME = sENSORBEGINTIME;
	}

	/**
	 * @return the sENSORENDTIME
	 */
	public String getSENSORENDTIME() {
		return SENSORENDTIME;
	}

	/**
	 * @param sENSORENDTIME
	 *            the sENSORENDTIME to set
	 */
	public void setSENSORENDTIME(String sENSORENDTIME) {
		SENSORENDTIME = sENSORENDTIME;
	}

	/**
	 * @return the sENSORORGAN
	 */
	public String getSENSORORGAN() {
		return SENSORORGAN;
	}

	/**
	 * @param sENSORORGAN
	 *            the sENSORORGAN to set
	 */
	public void setSENSORORGAN(String sENSORORGAN) {
		SENSORORGAN = sENSORORGAN;
	}

}
