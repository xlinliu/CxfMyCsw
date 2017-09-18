package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class AutomProcessType {
	private String uuid;// UUID
	private String name;// 名称
	private String xmlabstract;// 摘要
	private List<ComplexParamType> cpt = new ArrayList<ComplexParamType>();// 所有复杂参数对象
	private List<LiteralParamType> lpt = new ArrayList<LiteralParamType>();// 所有字面值参数对象
	private List<OutputParamType> opt = new ArrayList<OutputParamType>();// 所有输出参数类型对象

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXmlabstract() {
		return xmlabstract;
	}

	public void setXmlabstract(String xmlabstract) {
		this.xmlabstract = xmlabstract;
	}

	public List<ComplexParamType> getCpt() {
		return cpt;
	}

	public void setCpt(List<ComplexParamType> cpt) {
		this.cpt = cpt;
	}

	public List<LiteralParamType> getLpt() {
		return lpt;
	}

	public void setLpt(List<LiteralParamType> lpt) {
		this.lpt = lpt;
	}

	public List<OutputParamType> getOpt() {
		return opt;
	}

	public void setOpt(List<OutputParamType> opt) {
		this.opt = opt;
	}

}
