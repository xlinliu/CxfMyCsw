package com.csw.model.ebXMLModel;

import java.sql.Clob;
import java.util.Date;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-7-26 ����05:27:27
 */
public class SensorML {

	private long id;// �洢���Ǳ�Ƿ�
	private Clob sensorcontent;// �洢��sensorml������
	private String sensorid;// �洢����ebrim��ʽ�ı�ʶ��
	private String username;// �û�����
	private Date savetime;// �����������ʱ��

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Clob getSensorcontent() {
		return sensorcontent;
	}

	public void setSensorcontent(Clob sensorcontent) {
		this.sensorcontent = sensorcontent;
	}

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

}
