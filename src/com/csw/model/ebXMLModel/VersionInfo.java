package com.csw.model.ebXMLModel;

public class VersionInfo {
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

	// �������
	private String id;
	private String versionName;
	private String conmment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getConmment() {
		return conmment;
	}

	public void setConmment(String conmment) {
		this.conmment = conmment;
	}

}
