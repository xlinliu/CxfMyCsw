package com.csw.utils.custometypes;

/**
 * ������ͨ����������
 * 
 * @author yxliang
 * 
 */
public class SensorCommunicationProValueType {
	private String sensorid;//
	private String proname;
	private Object provalue;

	/**
	 * ��ȡ��������ʶ��
	 * 
	 * @return
	 */
	public String getSensorid() {
		return sensorid;
	}

	/**
	 * ���ô�������ʶ��
	 * 
	 */
	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	/**
	 * ��ȡ������ͨ����������
	 * 
	 * @return
	 */
	public String getProname() {
		return proname;
	}

	/**
	 * ���ô�����ͨ��������
	 * 
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}

	/**
	 * ��ȡ������ͨ������ֵ
	 * 
	 * @return
	 */
	public Object getProvalue() {
		return provalue;
	}

	/**
	 * ���ô�����ͨ������ֵ
	 * 
	 */
	public void setProvalue(Object provalue) {
		this.provalue = provalue;
	}

}
