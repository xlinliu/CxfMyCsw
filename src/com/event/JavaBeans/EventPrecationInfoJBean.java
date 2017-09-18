package com.event.JavaBeans;

import com.event.InnerEntities.PrecationInfo;

public class EventPrecationInfoJBean extends PrecationInfo {
	private int precationid;
	private String eventid;

	public int getPrecationid() {
		return precationid;
	}

	public void setPrecationid(int precationid) {
		this.precationid = precationid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
}
