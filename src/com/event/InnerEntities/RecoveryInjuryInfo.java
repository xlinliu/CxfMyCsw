package com.event.InnerEntities;

public class RecoveryInjuryInfo {
	private int injurenum;// 受伤人数(总和）
	private int seriousinjurenum;// 严重受伤人数
	private int minorinjurenum;// 轻微受伤人数
	private String detailurl;// 详细报道url

	public int getInjurenum() {
		return injurenum;
	}

	public void setInjurenum(int injurenum) {
		this.injurenum = injurenum;
	}

	public int getSeriousinjurenum() {
		return seriousinjurenum;
	}

	public void setSeriousinjurenum(int seriousinjurenum) {
		this.seriousinjurenum = seriousinjurenum;
	}

	public int getMinorinjurenum() {
		return minorinjurenum;
	}

	public void setMinorinjurenum(int minorinjurenum) {
		this.minorinjurenum = minorinjurenum;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
}
