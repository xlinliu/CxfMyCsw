package com.event.customTypes;

public class EventIdentificationInfo {
	private String eventID;// �¼����
	private String eventname;// �¼�����
	private String eventPeroid;// �¼������׶�
	private String eventDescription;// �¼�����
	private String processStatus;// �¼�����״̬

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
