package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 模型查询参数序列 
 * @author yxliang
 *
 */
@XmlRootElement(name = "ModelQueryParamList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModelQueryParamList {
	private List<ModelQueryParam> mqp=new ArrayList<ModelQueryParam>();

	public List<ModelQueryParam> getMqp() {
		return mqp;
	}

	public void setMqp(List<ModelQueryParam> mqp) {
		this.mqp = mqp;
	}
}
