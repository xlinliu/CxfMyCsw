package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

/**
 * SOS��Ϣ
 * 
 * @author Administrator
 * 
 */
public class SensorSOSInfo {
	/**
	 * sos��ַ
	 */
	private String sosurl;
	/**
	 * sos�Ļ�����
	 */
	private String sosoffering;
	/**
	 * sos�����Ĵ�����id
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
