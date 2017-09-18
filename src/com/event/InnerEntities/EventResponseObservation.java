package com.event.InnerEntities;

import java.util.HashSet;
import java.util.Set;

import com.event.JavaBeans.EventDenagedRoadJBean;

public class EventResponseObservation {
	// 道路损毁部分
	private Set<EventDenagedRoadJBean> rodsDemagedRoads = new HashSet<EventDenagedRoadJBean>();
	private String TrappedPerson;
	private PrecationInfo pInfo;

	public Set<EventDenagedRoadJBean> getRodsDemagedRoads() {
		return rodsDemagedRoads;
	}

	public void setRodsDemagedRoads(Set<EventDenagedRoadJBean> rodsDemagedRoads) {
		this.rodsDemagedRoads = rodsDemagedRoads;
	}

	public String getTrappedPerson() {
		return TrappedPerson;
	}

	public void setTrappedPerson(String trappedPerson) {
		TrappedPerson = trappedPerson;
	}

	public PrecationInfo getpInfo() {
		return pInfo;
	}

	public void setpInfo(PrecationInfo pInfo) {
		this.pInfo = pInfo;
	}
}
