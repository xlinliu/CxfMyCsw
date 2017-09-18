package com.csw.model.ebXMLModel;

public class Slot {
	/*
	 * 自定义主键
	 */
	private long outid;

	public long getOutid() {
		return outid;
	}

	public void setOutid(long outid) {
		this.outid = outid;
	}

	/*
	 * 自定义外键，用于关联slot所属的元素
	 */
	private String id;

	// required
	private String name;
	private String slotType;
	// required
	private String values;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

}
