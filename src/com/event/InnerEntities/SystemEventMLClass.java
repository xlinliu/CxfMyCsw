package com.event.InnerEntities;

import java.util.Date;

public class SystemEventMLClass extends EventMLClass {
	private String eventid;// �¼��ı��
	private String eventowner;// �¼���������
	private Date registertime;// �¼�ע���¼�
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
