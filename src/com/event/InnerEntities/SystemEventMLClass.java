package com.event.InnerEntities;

import java.util.Date;

public class SystemEventMLClass extends EventMLClass {
	private String eventid;// 事件的编号
	private String eventowner;// 事件的所有者
	private Date registertime;// 事件注册事件
	private EventMLClass emc;

	public SystemEventMLClass(EventMLClass emc) {
		this.emc = emc;
	}

	public EventMLClass getEmc() {
		return emc;
	}

	public void setEmc(EventMLClass emc) {
		this.emc = emc;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getEventowner() {
		return eventowner;
	}

	public void setEventowner(String eventowner) {
		this.eventowner = eventowner;
	}

	public Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}

}
