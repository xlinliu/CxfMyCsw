package com.service.customTypes;

public class ComponentServiceType {
	private ComplexProcess cpp = new ComplexProcess();// ���Ӵ������
	private Figures figrues = new Figures();// ����ֵ

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
