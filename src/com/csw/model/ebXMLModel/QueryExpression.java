package com.csw.model.ebXMLModel;

public class QueryExpression {

	/*
	 * ��������
	 */
	private long outid;

	public long getOutid() {
		return outid;
	}

	public void setOutid(long outid) {
		this.outid = outid;
	}

	/*
	 * �������
	 */
	private String id;
	private ObjectRef queryLanguage;
	private String anythings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObjectRef getQueryLanguage() {
		return queryLanguage;
	}

	public void setQueryLanguage(ObjectRef queryLanguage) {
		this.queryLanguage = queryLanguage;
	}

	public String getAnythings() {
		return anythings;
	}

	public void setAnythings(String anythings) {
		this.anythings = anythings;
	}

}
