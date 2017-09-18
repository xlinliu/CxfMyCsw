package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ComplexProcess {
	private List<AutomProcessType> ap = new ArrayList<AutomProcessType>();// 原子处理组件信息
	private List<ChainFlowType> cft = new ArrayList<ChainFlowType>();// 处理链信息
	private String uuid;

	public List<AutomProcessType> getAp() {
		return ap;
	}

	public void setAp(List<AutomProcessType> ap) {
		this.ap = ap;
	}

	public List<ChainFlowType> getCft() {
		return cft;
	}

	public void setCft(List<ChainFlowType> cft) {
		this.cft = cft;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
