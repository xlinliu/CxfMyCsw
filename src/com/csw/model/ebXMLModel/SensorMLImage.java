/**
 * 
 */
package com.csw.model.ebXMLModel;

import java.util.Date;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-6 ����10:29:40
 */
public class SensorMLImage {
	private int id;
	private String sensorid;// ��������id
	private String sensorimgurl;// ������ͼƬ�洢��ַ����ѡ��
	private Date savetime;// ����ʱ��
	private String attchmentmarker;// ������ʶ˵����������û��������׼�����������ȡ
	private String owner;// �����ṩ��

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getSensorimgurl() {
		return sensorimgurl;
	}

	public void setSensorimgurl(String sensorimgurl) {
		this.sensorimgurl = sensorimgurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	public String getAttchmentmarker() {
		return attchmentmarker;
	}

	public void setAttchmentmarker(String attchmentmarker) {
		this.attchmentmarker = attchmentmarker;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
