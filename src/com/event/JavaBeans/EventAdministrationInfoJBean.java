package com.event.JavaBeans;

import com.event.InnerEntities.AdministrationInfo;

public class EventAdministrationInfoJBean extends AdministrationInfo {
	private int administrationid;// 管理人员编号
	private String eventid;// 事件的编号

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public int getAdministrationid() {
		return administrationid;
	}

	public void setAdministrationid(int administrationid) {
		this.administrationid = administrationid;
	}

}
