package com.event.InnerEntities;

public class EventCasualty {
	private int totalcasualties;// 伤亡人数
	private RecoverybaseInfo deathrdi;// 死亡人数信息
	private RecoveryInjuryInfo riji;// 伤亡人数信息
	private RecoveryMissingInfo rmi;// 失踪人数信息

	public int getTotalcasualties() {
		return totalcasualties;
	}

	public void setTotalcasualties(int totalcasualties) {
		this.totalcasualties = totalcasualties;
	}

	public RecoverybaseInfo getDeathrdi() {
		return deathrdi;
	}

	public void setDeathrdi(RecoverybaseInfo deathrdi) {
		this.deathrdi = deathrdi;
	}

	public RecoveryInjuryInfo getRiji() {
		return riji;
	}

	public void setRiji(RecoveryInjuryInfo riji) {
		this.riji = riji;
	}

	public RecoveryMissingInfo getRmi() {
		return rmi;
	}

	public void setRmi(RecoveryMissingInfo rmi) {
		this.rmi = rmi;
	}

}
