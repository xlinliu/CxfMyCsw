package com.csw.utils.custometypes;

import java.util.List;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-17 ����09:32:42
 */
public class SensorTypeMarker {
	private String sensortype;// ���������͵�����(Ӣ�����ƣ�
	private String sensorchinesetype;// ��ȡ���������͵���������
	private List<String> sensorkeyword;// ��������İ����ַ���
	private Boolean isexist;// �Ƿ������ݿ��д���
	private String maker;// �ṩ������û�
	private String saveziduan;// �����ֶ�

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	public List<String> getSensorkeyword() {
		return sensorkeyword;
	}

	public void setSensorkeyword(List<String> sensorkeyword) {
		this.sensorkeyword = sensorkeyword;
	}

	public Boolean getIsexist() {
		return isexist;
	}

	public void setIsexist(Boolean isexist) {
		this.isexist = isexist;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getSaveziduan() {
		return saveziduan;
	}

	public void setSaveziduan(String saveziduan) {
		this.saveziduan = saveziduan;
	}

	public String getSensorchinesetype() {
		return sensorchinesetype;
	}

	public void setSensorchinesetype(String sensorchinesetype) {
		this.sensorchinesetype = sensorchinesetype;
	}
}
