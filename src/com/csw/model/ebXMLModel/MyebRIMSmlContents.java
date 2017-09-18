package com.csw.model.ebXMLModel;

public class MyebRIMSmlContents {
	// SensorML （续转换为ebRIM）或者ebRIM的id，这个是唯一的是sensorML转换为ebRIM的id
	private String id;
	private String ebrimContent;
	private String sensormlContent;
	private Integer outid;
	// 保存sensorml，ebrim的用户名或者用户id
	private String ower;
	// 上传文件名称时候的文件名称，不是通过文件上传的话，则可以通过上传的sensorml的id值截取出适合的文件名称
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
