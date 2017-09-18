package com.csw.model.ebXMLModel;

import java.util.HashSet;
import java.util.Set;

public class RegistryPackage extends RegistryObject {

	/*
	 * 自定一个RegistryPackage 属性用于存储registrypackageType中的set<identifiable>
	 */
	private Set<Identifiable> identifiables = new HashSet<Identifiable>();
	private String ower = "";

	public Set<Identifiable> getIdentifiables() {
		return identifiables;
	}

	public void setIdentifiables(Set<Identifiable> identifiables) {
		this.identifiables = identifiables;
	}

	public String getOwer() {
		return ower;
	}

	public void setOwer(String ower) {
		this.ower = ower;
	}

}
