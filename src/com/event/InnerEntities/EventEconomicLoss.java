package com.event.InnerEntities;

public class EventEconomicLoss {
	private int totalLossNumber;
	private RecoverybaseInfo directlossInfo;
	private RecoverybaseInfo indirectlossInfo;

	public int getTotalLossNumber() {
		return totalLossNumber;
	}

	public void setTotalLossNumber(int totalLossNumber) {
		this.totalLossNumber = totalLossNumber;
	}

	public RecoverybaseInfo getDirectlossInfo() {
		return directlossInfo;
	}

	public void setDirectlossInfo(RecoverybaseInfo directlossInfo) {
		this.directlossInfo = directlossInfo;
	}

	public RecoverybaseInfo getIndirectlossInfo() {
		return indirectlossInfo;
	}

	public void setIndirectlossInfo(RecoverybaseInfo indirectlossInfo) {
		this.indirectlossInfo = indirectlossInfo;
	}

}
