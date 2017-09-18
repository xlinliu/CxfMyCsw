package com.csw.model.ebXMLModel;

import java.sql.Clob;
import java.util.Date;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-7-26 下午05:27:27
 */
public class SensorML {

	private long id;// 存储的是标记符
	private Clob sensorcontent;// 存储的sensorml的内容
	private String sensorid;// 存储的是ebrim格式的标识符
	private String username;// 用户名称
	private Date savetime;// 传感器保存的时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Clob getSensorcontent() {
		return sensorcontent;
	}

	public void setSensorcontent(Clob sensorcontent) {
		this.sensorcontent = sensorcontent;
	}

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

}
