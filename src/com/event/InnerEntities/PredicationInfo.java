package com.event.InnerEntities;

public class PredicationInfo {
	private TimePeroid possibleTime;// �¼�������ʱ���
	private LatLongPiar possibleLocation;// �¼�������λ��
	private String possibleSeverity;// �¼����������س̶�

	public TimePeroid getPossibleTime() {
		return possibleTime;
	}

	public void setPossibleTime(TimePeroid possibleTime) {
		this.possibleTime = possibleTime;
	}

	public LatLongPiar getPossibleLocation() {
		return possibleLocation;
	}

	public void setPossibleLocation(LatLongPiar possibleLocation) {
		this.possibleLocation = possibleLocation;
	}

	public String getPossibleSeverity() {
		return possibleSeverity;
	}

	public void setPossibleSeverity(String possibleSeverity) {
		this.possibleSeverity = possibleSeverity;
	}

}
