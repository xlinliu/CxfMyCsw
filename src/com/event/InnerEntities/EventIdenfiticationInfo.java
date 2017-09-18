package com.event.InnerEntities;

import java.util.Date;

public class EventIdenfiticationInfo {
	private String eventid;// 事件的编号
	private String userid;// 事件上传者的编号
	private Date uploadtime;// 事件上传的时间
	private String eventname;// 事件的名称
	private String eventdesc;// 事件的描述

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
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
