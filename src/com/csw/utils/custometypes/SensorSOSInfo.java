package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

/**
 * SOS信息
 * 
 * @author Administrator
 * 
 */
public class SensorSOSInfo {
	/**
	 * sos地址
	 */
	private String sosurl;
	/**
	 * sos的机构号
	 */
	private String sosoffering;
	/**
	 * sos关联的传感器id
	 */
	private List<String> sosinfos = new ArrayList<String>();

	public String getSosurl() {
		return sosurl;
	}

	public void setSosurl(String sosurl) {
		this.sosurl = sosurl;
	}

	public String getSosoffering() {
		return sosoffering;
	}

	public void setSosoffering(String sosoffering) {
		this.sosoffering = sosoffering;
	}

	public List<String> getSosinfos() {
		return sosinfos;
	}

	public void setSosinfos(List<String> sosinfos) {
		this.sosinfos = sosinfos;
	}
}
