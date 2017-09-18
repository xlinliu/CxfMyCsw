package com.event.JavaBeans;

import com.event.InnerEntities.EventSpaceTime;

public class EventSpaceTimeJBean extends EventSpaceTime {
	private int spacetimeid;// 存储时空信息标号
	private String eventid;// 存储的事件id

	public int getSpacetimeid() {
		return spacetimeid;
	}

	public void setSpacetimeid(int spacetimeid) {
		this.spacetimeid = spacetimeid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
}
