package com.model.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ModelKeyWords {
	private String modelid;
	private String modelname;
	private List<String> keywords = new ArrayList<String>();

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
}
