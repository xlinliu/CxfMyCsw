package com.event.InnerEntities;

public class EventIdentification {
	private String eventid;// 事件的标识符
	private String eventname;// 事件的名称
	private String eventdesc;// 事件的描述

	public EventIdentification() {

	}

	public EventIdentification(String eventid, String eventname,
			String eventdesc) {
		super();
		this.eventid = eventid;
		this.eventname = eventname;
		this.eventdesc = eventdesc;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}
}
