package com.serviceresult.customTypes;

public class GeoPositionInfo {
	private GeoPointInfo lowerCorner = new GeoPointInfo();//位置lower部分 
	private GeoPointInfo upperCorner = new GeoPointInfo();//位置upper部分 

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
