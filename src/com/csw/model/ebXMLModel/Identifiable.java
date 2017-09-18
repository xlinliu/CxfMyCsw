package com.csw.model.ebXMLModel;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xunliangyang
 * 
 */
public class Identifiable {

	/*
	 * 自定义outid属性,主键
	 */
	private int outid;

	private String home;
	// required
	private String id;
	private Set<Slot> slots = new HashSet<Slot>();

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Slot> getSlots() {
		return slots;
	}

	public void setSlots(Set<Slot> slots) {
		this.slots = slots;
	}

	public int getOutid() {
		return outid;
	}

	public void setOutid(int outid) {
		this.outid = outid;
	}
}
