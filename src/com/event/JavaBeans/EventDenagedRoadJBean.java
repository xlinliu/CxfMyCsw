package com.event.JavaBeans;

import com.event.InnerEntities.ResponseObservation_DemagedRoad;

public class EventDenagedRoadJBean extends ResponseObservation_DemagedRoad {
	private int demagedRoadId;// ��ٵ�·���
	private String eventid;// �������¼��ı��

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
