package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ModelQueryParam {
	private ModelOperationTypes eot;// �¼���ѯ���ֶ�
	private List<String> value = new ArrayList<String>();// �����еĲ����еĲ���
	public ModelOperationTypes getEot() {
		return eot;
	}
	public void setEot(ModelOperationTypes eot) {
		this.eot = eot;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
}
