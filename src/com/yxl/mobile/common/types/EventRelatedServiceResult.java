package com.yxl.mobile.common.types;

import java.util.ArrayList;
import java.util.List;

public class EventRelatedServiceResult {
	private String serviceResultId;// 服务结果标识符
	private String serviceResultFullName;// 服务结果全称
	private String serviceResultShortName;// 服务结果简称
	private String serviceResultCreatedTime;// 服务结果创建时间
	private String serviceResultFormat;// 服务结果格式
	private List<String> serviceResultAddress = new ArrayList<String>();// 服务访问地址

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
