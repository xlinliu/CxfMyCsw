package com.event.JavaBeans;

import com.event.InnerEntities.EventResponseInfo;

public class EventResponseInfoJBean extends EventResponseInfo {
	private int responseid;
	private String eventid;

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public int getResponseid() {
		return responseid;
	}

	public void setResponseid(int responseid) {
		this.responseid = responseid;
	}

}
