package com.serviceresult.customTypes;

public class GeoPositionInfo {
	private GeoPointInfo lowerCorner = new GeoPointInfo();//λ��lower���� 
	private GeoPointInfo upperCorner = new GeoPointInfo();//λ��upper���� 

	public GeoPointInfo getLowerCorner() {
		return lowerCorner;
	}

	public void setLowerCorner(GeoPointInfo lowerCorner) {
		this.lowerCorner = lowerCorner;
	}

	public GeoPointInfo getUpperCorner() {
		return upperCorner;
	}

	public void setUpperCorner(GeoPointInfo upperCorner) {
		this.upperCorner = upperCorner;
	}
}
