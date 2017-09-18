package com.csw.model.ebXMLModel;

import java.util.HashSet;
import java.util.Set;


public class Subscription extends RegistryObject {

	private Set<NotifyAction> actions=new HashSet<NotifyAction>();
	private String endTime;
	private String notificationInterval;
	private ObjectRef selector;
	private String startTime;

	public Set<NotifyAction> getActions() {
		return actions;
	}

	public void setActions(Set<NotifyAction> actions) {
		this.actions = actions;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNotificationInterval() {
		return notificationInterval;
	}

	public void setNotificationInterval(String notificationInterval) {
		this.notificationInterval = notificationInterval;
	}

	public ObjectRef getSelector() {
		return selector;
	}

	public void setSelector(ObjectRef selector) {
		this.selector = selector;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}
