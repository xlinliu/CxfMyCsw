package com.event.customTypes;

import java.util.ArrayList;
import java.util.List;

public class EventQueryParam {

	private EventOperationTypes eot;// 事件查询的字段
	private List<String> value = new ArrayList<String>();// 所进行的操作中的部分

	public EventQueryParam() {
		// TODO Auto-generated constructor stub
	}

	public EventQueryParam(EventOperationTypes _eot, List<String> _params) {
		this.eot = _eot;
		this.value = _params;
	}

	public EventOperationTypes getEot() {
		return eot;
	}

	public void setEot(EventOperationTypes eot) {
		this.eot = eot;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
}
