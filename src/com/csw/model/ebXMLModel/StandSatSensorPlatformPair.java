package com.csw.model.ebXMLModel;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-9-18 ����02:36:36
 */
public class StandSatSensorPlatformPair {
	private int id;// ��ʶ��
	private String sensorname;// ��׼����������
	private String platform;// ��׼�����������ص�ƽ̨
	private double fukuai;// �������Ĺ۲����
	private String maker;// ������

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getFukuai() {
		return fukuai;
	}

	public void setFukuai(double fukuai) {
		this.fukuai = fukuai;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

}
