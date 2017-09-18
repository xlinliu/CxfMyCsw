package com.csw.utils.custometypes;

import java.util.List;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-9-17 上午09:32:42
 */
public class SensorTypeMarker {
	private String sensortype;// 传感器类型的名称(英文名称）
	private String sensorchinesetype;// 获取传感器类型的中文名称
	private List<String> sensorkeyword;// 类型里面的包含字符串
	private Boolean isexist;// 是否在数据库中存在
	private String maker;// 提供分类的用户
	private String saveziduan;// 保留字段

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
