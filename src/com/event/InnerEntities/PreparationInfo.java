package com.event.InnerEntities;

import com.event.JavaBeans.EventPredicationInfoJBean;

public class PreparationInfo {
	private PrecationInfo pInfo;// 预警的信息
	private AlterInfo ai;// 告警的信息
	private EventPredicationInfoJBean pi;// 预测的信息

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
