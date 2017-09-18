package com.csw.model.ebXMLModel;

import java.util.HashSet;
import java.util.Set;

public class InternationalString {

	/*
	 * 用于主键
	 */
	private long outid;

	public long getOutid() {
		return outid;
	}

	public void setOutid(long outid) {
		this.outid = outid;
	}

	// 自定义id，用于外键的表达
	private String id;

	private Set<LocalizedString> localizedStrings=new HashSet<LocalizedString>();

	public Set<LocalizedString> getLocalizedStrings() {
		return localizedStrings;
	}

	public void setLocalizedStrings(Set<LocalizedString> localizedStrings) {
		this.localizedStrings = localizedStrings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
