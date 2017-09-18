package com.event.customTypes;

import java.util.ArrayList;
import java.util.List;

public class EventQueryParam {

	private EventOperationTypes eot;// �¼���ѯ���ֶ�
	private List<String> value = new ArrayList<String>();// �����еĲ����еĲ���

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
