package com.event.JavaBeans;

import com.event.InnerEntities.PreparationInfo;

public class EventPreparationInfoJBean extends PreparationInfo {
	private int epijid;
	private String eventid;

	public int getEpijid() {
		return epijid;
	}

	public void setEpijid(int epijid) {
		this.epijid = epijid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
}
