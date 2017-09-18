package com.yxl.mobile.common.types;

import java.util.ArrayList;
import java.util.List;

public class EventRelatedServiceResult {
	private String serviceResultId;// ��������ʶ��
	private String serviceResultFullName;// ������ȫ��
	private String serviceResultShortName;// ���������
	private String serviceResultCreatedTime;// ����������ʱ��
	private String serviceResultFormat;// ��������ʽ
	private List<String> serviceResultAddress = new ArrayList<String>();// ������ʵ�ַ

	public String getServiceResultId() {
		return serviceResultId;
	}

	public void setServiceResultId(String serviceResultId) {
		this.serviceResultId = serviceResultId;
	}

	public String getServiceResultFullName() {
		return serviceResultFullName;
	}

	public void setServiceResultFullName(String serviceResultFullName) {
		this.serviceResultFullName = serviceResultFullName;
	}

	public String getServiceResultShortName() {
		return serviceResultShortName;
	}

	public void setServiceResultShortName(String serviceResultShortName) {
		this.serviceResultShortName = serviceResultShortName;
	}

	public String getServiceResultCreatedTime() {
		return serviceResultCreatedTime;
	}

	public void setServiceResultCreatedTime(String serviceResultCreatedTime) {
		this.serviceResultCreatedTime = serviceResultCreatedTime;
	}

	public String getServiceResultFormat() {
		return serviceResultFormat;
	}

	public void setServiceResultFormat(String serviceResultFormat) {
		this.serviceResultFormat = serviceResultFormat;
	}

	public List<String> getServiceResultAddress() {
		return serviceResultAddress;
	}

	public void setServiceResultAddress(List<String> serviceResultAddress) {
		this.serviceResultAddress = serviceResultAddress;
	}

}
