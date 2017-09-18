package com.csw.model.ebXMLModel;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-18 下午02:33:51
 */
public class SatOrbitJiSuanType {
	private int id;// 标识符
	private String satid;// 传感器的标识符
	private String maker;// 用户
	private StandSatSensorPlatformPair sspp;// 绑定的传感器的平台

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSatid() {
		return satid;
	}

	public void setSatid(String satid) {
		this.satid = satid;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public StandSatSensorPlatformPair getSspp() {
		return sspp;
	}

	public void setSspp(StandSatSensorPlatformPair sspp) {
		this.sspp = sspp;
	}

}
