package com.event.InnerEntities;

import java.util.Date;

public class EventIdenfiticationInfo {
	private String eventid;// �¼��ı��
	private String userid;// �¼��ϴ��ߵı��
	private Date uploadtime;// �¼��ϴ���ʱ��
	private String eventname;// �¼�������
	private String eventdesc;// �¼�������

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
