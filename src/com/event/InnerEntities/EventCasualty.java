package com.event.InnerEntities;

public class EventCasualty {
	private int totalcasualties;// ��������
	private RecoverybaseInfo deathrdi;// ����������Ϣ
	private RecoveryInjuryInfo riji;// ����������Ϣ
	private RecoveryMissingInfo rmi;// ʧ��������Ϣ

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
