package com.csw.model.ebXMLModel;

public class ClassificationScheme extends RegistryObject {

	private Boolean isInternal;
	private ObjectRef nodeType;

	public Boolean getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}

	public ObjectRef getNodeType() {
		return nodeType;
	}

	public void setNodeType(ObjectRef nodeType) {
		this.nodeType = nodeType;
	}

}
