package com.event.JavaBeans;

import java.util.Date;

/**
 * 该类主要是针对事件的基本信息，包括标识信息，分类信息和时空信息的存储
 * 
 * @author yxliang
 * 
 */
public class EventIdentificationJBean {
	private String eventid;// 事件的编号
	private String userid;// 事件上传者的编号
	private Date uploadtime;// 事件上传的时间
	private String eventname;// 事件的名称
	private String eventdesc;// 事件的描述
	private EventSpaceTimeJBean estj;// 事件的时空信息
	private EventClassificationJBean ecjb;// 事件的分类信息
	private String eventPhases;// 事件存储的事件的各阶段的信息,第1到4位数分别是precaution,preparation,response和recovery，1：表示对应的信息存在，0：表示对应的信息不存在

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

	public EventSpaceTimeJBean getEstj() {
		return estj;
	}

	public void setEstj(EventSpaceTimeJBean estj) {
		this.estj = estj;
	}

	public EventClassificationJBean getEcjb() {
		return ecjb;
	}

	public void setEcjb(EventClassificationJBean ecjb) {
		this.ecjb = ecjb;
	}

	public String getEventPhases() {
		return eventPhases;
	}

	public void setEventPhases(String eventPhases) {
		this.eventPhases = eventPhases;
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

}
