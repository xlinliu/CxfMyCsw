package com.csw.model.ebXMLModel;

/**
 *��Ŀ����:CxfMyCsw �����������崫�����Ĺ��� ������:Administrator ����ʱ��: 2013-9-16 ����10:13:25
 */
public class SensorTypeRule {
	private int id;// ��ʶ��
	private String sensortypename;// ���������͵�����(Ӣ�ĵģ�
	private String sensortypechinesename;// ���������͵���������
	private String sensorkeywords;// ���������͵Ĺؼ���
	private String saveziduan;// �����ֶ�
	private String maker;// ���������͹���Ķ�����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSensortypename() {
		return sensortypename;
	}

	public void setSensortypename(String sensortypename) {
		this.sensortypename = sensortypename;
	}

	public String getSensorkeywords() {
		return sensorkeywords;
	}

	public void setSensorkeywords(String sensorkeywords) {
		this.sensorkeywords = sensorkeywords;
	}

	public String getSaveziduan() {
		return saveziduan;
	}

	public void setSaveziduan(String saveziduan) {
		this.saveziduan = saveziduan;
	}

	public String getSensortypechinesename() {
		return sensortypechinesename;
	}

	public void setSensortypechinesename(String sensortypechinesename) {
		this.sensortypechinesename = sensortypechinesename;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}
}
