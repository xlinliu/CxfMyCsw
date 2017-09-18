package com.event.JavaBeans;

import com.event.InnerEntities.ResponseObservation_DemagedRoad;

public class EventDenagedRoadJBean extends ResponseObservation_DemagedRoad {
	private int demagedRoadId;// 损毁道路编号
	private String eventid;// 所属的事件的标号

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public int getDemagedRoadId() {
		return demagedRoadId;
	}

	public void setDemagedRoadId(int demagedRoadId) {
		this.demagedRoadId = demagedRoadId;
	}
}
