package com.event.customTypes;

public class EventIdentificationInfo {
	private String eventID;// 事件编号
	private String eventname;// 事件名称
	private String eventPeroid;// 事件发生阶段
	private String eventDescription;// 事件描述
	private String processStatus;// 事件处理状态

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getEventPeroid() {
		return eventPeroid;
	}

	public void setEventPeroid(String eventPeroid) {
		this.eventPeroid = eventPeroid;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
}
