package com.csw.utils.custometypes;

import java.util.ArrayList;
import java.util.List;

public class PlatformandSensorsYXL {
	private String platformid;
	private List<String> sensorids = new ArrayList<String>();

	public String getPlatformid() {
		return platformid;
	}

	public void setPlatformid(String platformid) {
		this.platformid = platformid;
	}

	public List<String> getSensorids() {
		return sensorids;
	}

	public void setSensorids(List<String> sensorids) {
		this.sensorids = sensorids;
	}
}
