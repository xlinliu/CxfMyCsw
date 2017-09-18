package com.event.InnerEntities;

public class EventResponseInfo {
	private EventResponseObservation erop;
	private EventResponseResource epr;
	private EventResponseStateReport ersr;
	private EventResponseTrendPredition ertp;

	public EventResponseObservation getErop() {
		return erop;
	}

	public void setErop(EventResponseObservation erop) {
		this.erop = erop;
	}

	public EventResponseResource getEpr() {
		return epr;
	}

	public void setEpr(EventResponseResource epr) {
		this.epr = epr;
	}

	public EventResponseStateReport getErsr() {
		return ersr;
	}

	public void setErsr(EventResponseStateReport ersr) {
		this.ersr = ersr;
	}

	public EventResponseTrendPredition getErtp() {
		return ertp;
	}

	public void setErtp(EventResponseTrendPredition ertp) {
		this.ertp = ertp;
	}

}
