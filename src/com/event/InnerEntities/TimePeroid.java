package com.event.InnerEntities;

import java.util.Date;

public class TimePeroid {
	private Date startime;// 开始时间
	private Date endtime;// 结束时间

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
