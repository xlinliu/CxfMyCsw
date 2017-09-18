package com.csw.model.ebXMLModel;

public class TelephoneNumber {
	/*
	 * 用于主键
	 */
	private long outid;

	public long getOutid() {
		return outid;
	}

	public void setOutid(long outid) {
		this.outid = outid;
	}

	/*
	 * 用于外键
	 */
	private String id;
	private String areaCode;
	private String countryCode;
	private String extersion;
	private String number;
	private ObjectRef phoneType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getExtersion() {
		return extersion;
	}

	public void setExtersion(String extersion) {
		this.extersion = extersion;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ObjectRef getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(ObjectRef phoneType) {
		this.phoneType = phoneType;
	}

}
