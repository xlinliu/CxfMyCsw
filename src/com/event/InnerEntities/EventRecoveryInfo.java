package com.event.InnerEntities;

public class EventRecoveryInfo {
	private EventCasualty ec;
	private EventEconomicLoss eel;
	private EventOtherInfluence oif;

	public EventCasualty getEc() {
		return ec;
	}

	public void setEc(EventCasualty ec) {
		this.ec = ec;
	}

	public EventEconomicLoss getEel() {
		return eel;
	}

	public void setEel(EventEconomicLoss eel) {
		this.eel = eel;
	}

	public EventOtherInfluence getOif() {
		return oif;
	}

	public void setOif(EventOtherInfluence oif) {
		this.oif = oif;
	}
}
