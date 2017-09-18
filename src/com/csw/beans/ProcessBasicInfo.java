package com.csw.beans;

public class ProcessBasicInfo {
	private int outid;
	private String processId;
	private String ProcessType;
	private String intendedApplication;
	private String serviceType;

	public int getOutid() {
		return outid;
	}

	public void setOutid(int outid) {
		this.outid = outid;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessType() {
		return ProcessType;
	}

	public void setProcessType(String processType) {
		ProcessType = processType;
	}

	public String getIntendedApplication() {
		return intendedApplication;
	}

	public void setIntendedApplication(String intendedApplication) {
		this.intendedApplication = intendedApplication;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}
