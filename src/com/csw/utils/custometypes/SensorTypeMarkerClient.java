package com.csw.utils.custometypes;

/**
 *项目名称:CxfMyCsw 类描述：该类不需要增加对所属者的应用，因为该用户会获取到特定的传感器的类型 创建人:Administrator 创建时间:
 * 2013-9-17 上午10:30:13
 */
public class SensorTypeMarkerClient {
	private String sensortype;// 传感器类型的名称(英文名称）
	private String sensorchinesetype;// 获取传感器类型的中文名称
	private String sensorkeyword;// 类型里面的包含字符串以逗号隔开
	private String saveziduan;// 保留字段

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

	public String getSensorchinesetype() {
		return sensorchinesetype;
	}

	public void setSensorchinesetype(String sensorchinesetype) {
		this.sensorchinesetype = sensorchinesetype;
	}

	public String getSensorkeyword() {
		return sensorkeyword;
	}

	public void setSensorkeyword(String sensorkeyword) {
		this.sensorkeyword = sensorkeyword;
	}

	public String getSaveziduan() {
		return saveziduan;
	}

	public void setSaveziduan(String saveziduan) {
		this.saveziduan = saveziduan;
	}

}
