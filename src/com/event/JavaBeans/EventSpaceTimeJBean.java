package com.event.JavaBeans;

import com.event.InnerEntities.EventSpaceTime;

public class EventSpaceTimeJBean extends EventSpaceTime {
	private int spacetimeid;// �洢ʱ����Ϣ���
	private String eventid;// �洢���¼�id

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
