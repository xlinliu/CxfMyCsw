package com.csw.model.ebXMLModel;

public class Registry extends RegistryObject {

	private String catalogingLatency;
	private String conformanceProfile;
	private ObjectRef operator;
	private String replicationSynLatency;
	private String specificationVersion;

	public String getCatalogingLatency() {
		return catalogingLatency;
	}

	public void setCatalogingLatency(String catalogingLatency) {
		this.catalogingLatency = catalogingLatency;
	}

	public String getConformanceProfile() {
		return conformanceProfile;
	}

	public void setConformanceProfile(String conformanceProfile) {
		this.conformanceProfile = conformanceProfile;
	}

	public ObjectRef getOperator() {
		return operator;
	}

	public void setOperator(ObjectRef operator) {
		this.operator = operator;
	}

	public String getReplicationSynLatency() {
		return replicationSynLatency;
	}

	public void setReplicationSynLatency(String replicationSynLatency) {
		this.replicationSynLatency = replicationSynLatency;
	}

	public String getSpecificationVersion() {
		return specificationVersion;
	}

	public void setSpecificationVersion(String specificationVersion) {
		this.specificationVersion = specificationVersion;
	}

}
