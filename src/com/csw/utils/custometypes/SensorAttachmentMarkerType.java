/**
 * 
 */
package com.csw.utils.custometypes;

import java.util.Date;


/**
 *��Ŀ����:CxfMyCsw �����������ڷ��ظ�����ַ�͸���˵�� ������:Administrator ����ʱ��: 2013-9-9 ����10:24:15
 */
public class SensorAttachmentMarkerType {
	private String sensorid;// �������ı�ʶ��
	private String sensorattachementpath;// ����������web·��
	private String sensorattachementmarker;// ����������
	private SensorAttachmentType img;// ����������
	private Date sensormlsaveDate;// ��������������ʱ��

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getSensorattachementpath() {
		return sensorattachementpath;
	}

	public void setSensorattachementpath(String sensorattachementpath) {
		this.sensorattachementpath = sensorattachementpath;
	}

	public String getSensorattachementmarker() {
		return sensorattachementmarker;
	}

	public void setSensorattachementmarker(String sensorattachementmarker) {
		this.sensorattachementmarker = sensorattachementmarker;
	}

	public SensorAttachmentType getImg() {
		return img;
	}

	public void setImg(SensorAttachmentType img) {
		this.img = img;
	}

	public Date getSensormlsaveDate() {
		return sensormlsaveDate;
	}

	public void setSensormlsaveDate(Date sensormlsaveDate) {
		this.sensormlsaveDate = sensormlsaveDate;
	}

}
