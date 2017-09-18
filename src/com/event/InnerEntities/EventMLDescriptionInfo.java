package com.event.InnerEntities;

public class EventMLDescriptionInfo {
	private PrecationInfo precationInfo;
	private PreparationInfo preparationInfo;
	private EventRecoveryInfo recoveryInfo;
	private EventResponseInfo responseInfo;

	public PrecationInfo getPrecationInfo() {
		return precationInfo;
	}

	public void setPrecationInfo(PrecationInfo precationInfo) {
		this.precationInfo = precationInfo;
	}

	public PreparationInfo getPreparationInfo() {
		return preparationInfo;
	}

	public void setPreparationInfo(PreparationInfo preparationInfo) {
		this.preparationInfo = preparationInfo;
	}

	public EventRecoveryInfo getRecoveryInfo() {
		return recoveryInfo;
	}

	public void setRecoveryInfo(EventRecoveryInfo recoveryInfo) {
		this.recoveryInfo = recoveryInfo;
	}

	public EventResponseInfo getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(EventResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}

}
