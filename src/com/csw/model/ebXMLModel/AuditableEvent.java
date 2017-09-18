package com.csw.model.ebXMLModel;

import java.util.HashSet;
import java.util.Set;

public class AuditableEvent extends RegistryObject {

	private ObjectRef eventType;
	private Set<ObjectRef> affectedObjects = new HashSet<ObjectRef>();
	private String requestId;
	private String timestamp;
	private ObjectRef user;

	public ObjectRef getEventType() {
		return eventType;
	}

	public void setEventType(ObjectRef eventType) {
		this.eventType = eventType;
	}

	public Set<ObjectRef> getAffectedObjects() {
		return affectedObjects;
	}

	public void setAffectedObjects(Set<ObjectRef> affectedObjects) {
		this.affectedObjects = affectedObjects;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public ObjectRef getUser() {
		return user;
	}

	public void setUser(ObjectRef user) {
		this.user = user;
	}

}
