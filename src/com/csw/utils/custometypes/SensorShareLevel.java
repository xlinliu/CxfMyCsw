/**
 * 
 */
package com.csw.utils.custometypes;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-15 ����03:08:40
 */
public class SensorShareLevel {
	private String sensorid;// �������ı�ʶ��
	private String sharelevel;// �������ķ�����
	private Boolean isShare;// �������Ƿ�ɹ���
	private String sensorowner;// ��������������

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
