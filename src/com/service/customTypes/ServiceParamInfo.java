package com.service.customTypes;

/**
 * 服务参数说明
 * 
 * @author yxliang
 * 
 */
public class ServiceParamInfo {
	private String paramId;// 参数标识符
	private String paramtype;// 参数类型
	private String paradesc;// 参数描述

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getParamtype() {
		return paramtype;
	}

	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}

	public String getParadesc() {
		return paradesc;
	}

	public void setParadesc(String paradesc) {
		this.paradesc = paradesc;
	}
}
