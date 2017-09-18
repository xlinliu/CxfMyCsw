package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * ģ�ͳ�������
 * 
 * @author yxliang
 * 
 */
public class ModelBasicInfo {
	private String docname;// �洢��berkeley xml �е�doc������
	private List<String> keywords = new ArrayList<String>();
	private String modelID;// ģ�͵ı�ʶ��
	private String fullName;// ģ�͵�ȫ��
	private String shortName;// ģ�͵ļ��
	private String modelType;// ģ�͵�����
	private String modelSubType;// ģ�͵�������
	private String modelLevel;// ģ�͵ļ���
	private List<String> modelDomain = new ArrayList<String>();// ģ�͵�Ӧ������
	private String function;// ģ�͵Ĺ���
	private String modelperson;// ģ����ϵ��
	private String modelorganname;// ģ����ϵ��λ
	private Double northvalue;// ��γ
	private Double eastvalue;// ����
	private Double southvalue;// ��γ
	private Double westvalue;// ����
	private String preciseunit;// ģ�;��ȵ�λ
	private String precisevalue;// ģ�;���ֵ
	private String performanceType;// ģ����������

	public String getShortName() {
		return shortName;
	}

	public String getPerformanceType() {
		return performanceType;
	}

	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getModelID() {
		return modelID;
	}

	public void setModelID(String modelID) {
		this.modelID = modelID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getModelSubType() {
		return modelSubType;
	}

	public void setModelSubType(String modelSubType) {
		this.modelSubType = modelSubType;
	}

	public String getModelLevel() {
		return modelLevel;
	}

	public void setModelLevel(String modelLevel) {
		this.modelLevel = modelLevel;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getModelDomain() {
		return modelDomain;
	}

	public void setModelDomain(List<String> modelDomain) {
		this.modelDomain = modelDomain;
	}

	public String getModelperson() {
		return modelperson;
	}

	public void setModelperson(String modelperson) {
		this.modelperson = modelperson;
	}

	public String getModelorganname() {
		return modelorganname;
	}

	public void setModelorganname(String modelorganname) {
		this.modelorganname = modelorganname;
	}

	public Double getNorthvalue() {
		return northvalue;
	}

	public void setNorthvalue(Double northvalue) {
		this.northvalue = northvalue;
	}

	public Double getEastvalue() {
		return eastvalue;
	}

	public void setEastvalue(Double eastvalue) {
		this.eastvalue = eastvalue;
	}

	public Double getSouthvalue() {
		return southvalue;
	}

	public void setSouthvalue(Double southvalue) {
		this.southvalue = southvalue;
	}

	public String getPreciseunit() {
		return preciseunit;
	}

	public void setPreciseunit(String preciseunit) {
		this.preciseunit = preciseunit;
	}

	public String getPrecisevalue() {
		return precisevalue;
	}

	public void setPrecisevalue(String precisevalue) {
		this.precisevalue = precisevalue;
	}

	public Double getWestvalue() {
		return westvalue;
	}

	public void setWestvalue(Double westvalue) {
		this.westvalue = westvalue;
	}
}
