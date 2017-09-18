package com.csw.model.ebXMLModel;

public class ExtrinsicObject extends RegistryObject {

	private boolean isOpaque;
	private String mimeType;
	private VersionInfo contentVersionInfo;
	// ExtrinsicObject的name类型为localizedString类型，故加一个属性 lname
	private LocalizedString lname;

	public LocalizedString getLname() {
		return lname;
	}

	public void setLname(LocalizedString lname) {
		this.lname = lname;
	}

	public void setOpaque(boolean isOpaque) {
		this.isOpaque = isOpaque;
	}

	public boolean getIsOpaque() {
		return isOpaque;
	}

	public void setIsOpaque(boolean isOpaque) {
		this.isOpaque = isOpaque;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public VersionInfo getContentVersionInfo() {
		return contentVersionInfo;
	}

	public void setContentVersionInfo(VersionInfo contentVersionInfo) {
		this.contentVersionInfo = contentVersionInfo;
	}

}
