package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class AutomProcessType {
	private String uuid;// UUID
	private String name;// ����
	private String xmlabstract;// ժҪ
	private List<ComplexParamType> cpt = new ArrayList<ComplexParamType>();// ���и��Ӳ�������
	private List<LiteralParamType> lpt = new ArrayList<LiteralParamType>();// ��������ֵ��������
	private List<OutputParamType> opt = new ArrayList<OutputParamType>();// ��������������Ͷ���

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
