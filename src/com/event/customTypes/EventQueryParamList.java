package com.event.customTypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EventQueryParamList")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventQueryParamList {
	private List<EventQueryParam> eqps = new ArrayList<EventQueryParam>();

	public List<EventQueryParam> getEqps() {
		return eqps;
	}

	public void setEqps(List<EventQueryParam> eqps) {
		this.eqps = eqps;
	}
}
