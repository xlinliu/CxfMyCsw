package com.service.customTypes;

public class ComponentServiceType {
	private ComplexProcess cpp = new ComplexProcess();// 复杂处理过程
	private Figures figrues = new Figures();// 特征值

	public ComplexProcess getCpp() {
		return cpp;
	}

	public void setCpp(ComplexProcess cpp) {
		this.cpp = cpp;
	}

	public Figures getFigrues() {
		return figrues;
	}

	public void setFigrues(Figures figrues) {
		this.figrues = figrues;
	}
}
