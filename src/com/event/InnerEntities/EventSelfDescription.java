package com.event.InnerEntities;

public class EventSelfDescription {
	private EventIdentification eif = new EventIdentification();
	private EventSpaceTime est = new EventSpaceTime();
	private EventClassification ecf = new EventClassification();

	public EventIdentification getEif() {
		return eif;
	}

	public void setEif(EventIdentification eif) {
		this.eif = eif;
	}

	public EventSpaceTime getEst() {
		return est;
	}

	public void setEst(EventSpaceTime est) {
		this.est = est;
	}

	public EventClassification getEcf() {
		return ecf;
	}

	public void setEcf(EventClassification ecf) {
		this.ecf = ecf;
	}
}
