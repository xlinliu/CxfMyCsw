package com.event.InnerEntities;

import com.event.JavaBeans.EventPredicationInfoJBean;

public class PreparationInfo {
	private PrecationInfo pInfo;// Ԥ������Ϣ
	private AlterInfo ai;// �澯����Ϣ
	private EventPredicationInfoJBean pi;// Ԥ�����Ϣ

	public PrecationInfo getpInfo() {
		return pInfo;
	}

	public void setpInfo(PrecationInfo pInfo) {
		this.pInfo = pInfo;
	}

	public AlterInfo getAi() {
		return ai;
	}

	public void setAi(AlterInfo ai) {
		this.ai = ai;
	}

	public EventPredicationInfoJBean getPi() {
		return pi;
	}

	public void setPi(EventPredicationInfoJBean pi) {
		this.pi = pi;
	}
}
