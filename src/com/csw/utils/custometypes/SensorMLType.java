/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.Date;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-16 ����05:18:05
 */
public class SensorMLType {

	private String sensorid;// ��������ʶ��
	private String owner;// ������������
	private Date savetime;// ����ʱ��

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
