package com.event.JavaBeans;

import com.event.InnerEntities.EventRecoveryInfo;

public class EventRecoveryInfoJBean extends EventRecoveryInfo {
	private int recoveryid;
	private String eventid;

	public int getRecoveryid() {
		return recoveryid;
	}

	public void setRecoveryid(int recoveryid) {
		this.recoveryid = recoveryid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
}
