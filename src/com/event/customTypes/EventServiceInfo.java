package com.event.customTypes;

public class EventServiceInfo {
	private String eventServiceName;//事件关联服务名称
	private String eventServiceType;//事件关联服务类型
	private String eventserviceAddress;//事件关联服务访问地址
	private String eventMoreInfo;//事件更多信息

	public String getEventServiceName() {
		return eventServiceName;
	}

	public void setEventServiceName(String eventServiceName) {
		this.eventServiceName = eventServiceName;
	}

	public String getEventServiceType() {
		return eventServiceType;
	}

	public void setEventServiceType(String eventServiceType) {
		this.eventServiceType = eventServiceType;
	}

	public String getEventserviceAddress() {
		return eventserviceAddress;
	}

	public void setEventserviceAddress(String eventserviceAddress) {
		this.eventserviceAddress = eventserviceAddress;
	}

	public String getEventMoreInfo() {
		return eventMoreInfo;
	}

	public void setEventMoreInfo(String eventMoreInfo) {
		this.eventMoreInfo = eventMoreInfo;
	}

}
