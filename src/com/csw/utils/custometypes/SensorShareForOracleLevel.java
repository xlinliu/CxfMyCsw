/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-27 ����07:56:18
 */
public class SensorShareForOracleLevel {
	private String SENSORID;// ��������ʶ��
	private String SENSORSHARELEVEL;// ������������
	private String SENSOROWNER;// ������������

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
