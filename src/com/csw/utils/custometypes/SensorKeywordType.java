package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

public class SensorKeywordType {
	private String sensorid;
	private List<String> keywords = new ArrayList<String>();

	public String getSensorid() {
		return sensorid;
	}

	public void setSensorid(String sensorid) {
		this.sensorid = sensorid;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

}
