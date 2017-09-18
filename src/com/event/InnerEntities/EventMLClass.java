package com.event.InnerEntities;

public class EventMLClass {

	private AdministrationInfo ai;// 事件管理者信息
	private EventSelfDescription esd;// 事件自我描述对象
	private EventMLDescriptionInfo emdi;// 对事件的描述

	public AdministrationInfo getAi() {
		return ai;
	}

	public void setAi(AdministrationInfo ai) {
		this.ai = ai;
	}

	public EventSelfDescription getEsd() {
		return esd;
	}

	public void setEsd(EventSelfDescription esd) {
		this.esd = esd;
	}

	public EventMLDescriptionInfo getEmdi() {
		return emdi;
	}

	public void setEmdi(EventMLDescriptionInfo emdi) {
		this.emdi = emdi;
	}
}
