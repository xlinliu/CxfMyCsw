package com.csw.model.ebXMLModel;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午02:36:36
 */
public class StandSatSensorPlatformPair {
	private int id;// 标识符
	private String sensorname;// 标准传感器名称
	private String platform;// 标准传感器所搭载的平台
	private double fukuai;// 传感器的观测幅宽
	private String maker;// 创建者

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
