package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ProcessFigureType {
	private String uuid;
	private String locXY;
	private List<DataFigureType> dft = new ArrayList<DataFigureType>();

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLocXY() {
		return locXY;
	}

	public void setLocXY(String locXY) {
		this.locXY = locXY;
	}

	public List<DataFigureType> getDft() {
		return dft;
	}

	public void setDft(List<DataFigureType> dft) {
		this.dft = dft;
	}
}
