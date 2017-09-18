package com.csw.model.ebXMLModel;

public class SpecificationLink extends RegistryObject {

	private ObjectRef serviceBinding;

	private ObjectRef specificationObject;
	private InternationalString usageDescription;
	/*
	 * 多个参数以逗号隔开
	 */
	private String usageParameters;

	public ObjectRef getServiceBinding() {
		return serviceBinding;
	}

	public void setServiceBinding(ObjectRef serviceBinding) {
		this.serviceBinding = serviceBinding;
	}

	public ObjectRef getSpecificationObject() {
		return specificationObject;
	}

	public void setSpecificationObject(ObjectRef specificationObject) {
		this.specificationObject = specificationObject;
	}

	public InternationalString getUsageDescription() {
		return usageDescription;
	}

	public void setUsageDescription(InternationalString usageDescription) {
		this.usageDescription = usageDescription;
	}

	public String getUsageParameters() {
		return usageParameters;
	}

	public void setUsageParameters(String usageParameters) {
		this.usageParameters = usageParameters;
	}

}
