package com.csw.model.ebXMLModel;

/**
 *项目名称:CxfMyCsw 类描述：定义传感器的规则 创建人:Administrator 创建时间: 2013-9-16 下午10:13:25
 */
public class SensorTypeRule {
	private int id;// 标识符
	private String sensortypename;// 传感器类型的名称(英文的）
	private String sensortypechinesename;// 传感器类型的中文名称
	private String sensorkeywords;// 传感器类型的关键字
	private String saveziduan;// 保留字段
	private String maker;// 传感器类型规则的定义者

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
