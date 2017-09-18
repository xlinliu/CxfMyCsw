/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-25 ����04:16:07
 */
public class SensorBasciForOracleType implements java.lang.Cloneable {
	private String SENSORID;// ��������ʶ��
	private String SENSORLONGNAME;// ������ȫ��
	private String SENSORSHORTNAME;// ���������
	private String SENSORTYPE;// ����������
	private String SENSORINTENDAPP;// ������Ԥ��Ӧ��
	private String SENSORKEYWORD;// �������ؼ���
	private String SENSORBBOX;// �������۲ⷶΧ
	private String SENSORPOS;// ������λ��
	private String SENSORBEGINTIME;// ��������ʼ����ʱ��
	private String SENSORENDTIME;// ��������������ʱ��
	private String SENSORORGAN;// ����������֯��Ϣ
	private String SENSOROWNER;// ������������
	private String SENSOROPERABLE;// �������ɿ���
	private String SENSORPUBLIC;// ������������
	
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
