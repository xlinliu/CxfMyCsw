package com.event.InnerEntities;

import java.util.Date;

public class TimePeroid {
	private Date startime;// ��ʼʱ��
	private Date endtime;// ����ʱ��

	public TimePeroid(Date startime, Date endtime) {
		this.startime = startime;
		this.endtime = endtime;
	}

	public TimePeroid() {
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}
