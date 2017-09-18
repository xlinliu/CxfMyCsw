package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型常见类型
 * 
 * @author yxliang
 * 
 */
public class ModelBasicInfo {
	private String docname;// 存储在berkeley xml 中的doc的名称
	private List<String> keywords = new ArrayList<String>();
	private String modelID;// 模型的标识符
	private String fullName;// 模型的全称
	private String shortName;// 模型的简称
	private String modelType;// 模型的类型
	private String modelSubType;// 模型的子类型
	private String modelLevel;// 模型的级别
	private List<String> modelDomain = new ArrayList<String>();// 模型的应用领域
	private String function;// 模型的功能
	private String modelperson;// 模型联系人
	private String modelorganname;// 模型联系单位
	private Double northvalue;// 北纬
	private Double eastvalue;// 东经
	private Double southvalue;// 南纬
	private Double westvalue;// 西经
	private String preciseunit;// 模型精度单位
	private String precisevalue;// 模型精度值
	private String performanceType;// 模型性能类型

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
