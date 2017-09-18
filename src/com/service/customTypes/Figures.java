package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class Figures {

	private List<ProcessFigureType> pft = new ArrayList<ProcessFigureType>();
	private List<FlowFigureType> fft = new ArrayList<FlowFigureType>();

	public List<ProcessFigureType> getPft() {
		return pft;
	}

	public void setPft(List<ProcessFigureType> pft) {
		this.pft = pft;
	}

	public List<FlowFigureType> getFft() {
		return fft;
	}

	public void setFft(List<FlowFigureType> fft) {
		this.fft = fft;
	}
}
