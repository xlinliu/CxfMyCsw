package com.event.InnerEntities;

public class PredicationInfo {
	private TimePeroid possibleTime;// 事件发生的时间段
	private LatLongPiar possibleLocation;// 事件发生的位置
	private String possibleSeverity;// 事件发生的严重程度

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
