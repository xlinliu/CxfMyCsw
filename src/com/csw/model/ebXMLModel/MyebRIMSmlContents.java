package com.csw.model.ebXMLModel;

public class MyebRIMSmlContents {
	// SensorML ����ת��ΪebRIM������ebRIM��id�������Ψһ����sensorMLת��ΪebRIM��id
	private String id;
	private String ebrimContent;
	private String sensormlContent;
	private Integer outid;
	// ����sensorml��ebrim���û��������û�id
	private String ower;
	// �ϴ��ļ�����ʱ����ļ����ƣ�����ͨ���ļ��ϴ��Ļ��������ͨ���ϴ���sensorml��idֵ��ȡ���ʺϵ��ļ�����
	private String filename;
	private String dateTime;

	public Integer getOutid() {
		return outid;
	}

	public void setOutid(Integer outid) {
		this.outid = outid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEbrimContent() {
		return ebrimContent;
	}

	public void setEbrimContent(String ebrimContent) {
		this.ebrimContent = ebrimContent;
	}

	public String getSensormlContent() {
		return sensormlContent;
	}

	public void setSensormlContent(String sensormlContent) {
		this.sensormlContent = sensormlContent;
	}

	public String getOwer() {
		return ower;
	}

	public void setOwer(String ower) {
		this.ower = ower;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
