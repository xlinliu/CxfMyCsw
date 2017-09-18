package com.event.InnerEntities;

import java.util.Date;

public class AlterInfo {

	private Date alerttime;// 事件发生时间
	private String alertstatus;// 事件状态
	private String alertmessagetype;// 事件信息类型
	private String alertscope;// 事件发生范围
	private DetailAddress alertArea;// 事件发生区域
	private String alertMessage;// 事件消息描述

	public Date getAlerttime() {
		return alerttime;
	}

	public void setAlerttime(Date alerttime) {
		this.alerttime = alerttime;
	}

	public String getAlertstatus() {
		return alertstatus;
	}

	public void setAlertstatus(String alertstatus) {
		this.alertstatus = alertstatus;
	}

	public String getAlertmessagetype() {
		return alertmessagetype;
	}

	public void setAlertmessagetype(String alertmessagetype) {
		this.alertmessagetype = alertmessagetype;
	}

	public String getAlertscope() {
		return alertscope;
	}

	public void setAlertscope(String alertscope) {
		this.alertscope = alertscope;
	}

	public DetailAddress getAlertArea() {
		return alertArea;
	}

	public void setAlertArea(DetailAddress alertArea) {
		this.alertArea = alertArea;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

}
