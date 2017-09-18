package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ModelQueryParam {
	private ModelOperationTypes eot;// 事件查询的字段
	private List<String> value = new ArrayList<String>();// 所进行的操作中的部分
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
