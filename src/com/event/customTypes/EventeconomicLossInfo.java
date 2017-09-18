package com.event.customTypes;

public class EventeconomicLossInfo {
	private String totalLossesNumber_unit;// 总经济损失单位
	private Double totalLossesNumber;// 总经济损失
	private String directLossesNumber_unit;// 直接经济损失
	private Double directLossesNumber;// 经济经济损失
	private String detailedDirectLossesInfoURL;// 直接经济损失信息URL
	private String indirectLossesNumber_unit;// 间接经济损失
	private Double indirectLossesNumber;// 间接经济损失
	private String detailedIndirectLossesInfoURL;// 间接经济损失的url

	public String getTotalLossesNumber_unit() {
		return totalLossesNumber_unit;
	}

	public void setTotalLossesNumber_unit(String totalLossesNumber_unit) {
		this.totalLossesNumber_unit = totalLossesNumber_unit;
	}

	public Double getTotalLossesNumber() {
		return totalLossesNumber;
	}

	public void setTotalLossesNumber(Double totalLossesNumber) {
		this.totalLossesNumber = totalLossesNumber;
	}

	public String getDirectLossesNumber_unit() {
		return directLossesNumber_unit;
	}

	public void setDirectLossesNumber_unit(String directLossesNumber_unit) {
		this.directLossesNumber_unit = directLossesNumber_unit;
	}

	public Double getDirectLossesNumber() {
		return directLossesNumber;
	}

	public void setDirectLossesNumber(Double directLossesNumber) {
		this.directLossesNumber = directLossesNumber;
	}

	public String getDetailedDirectLossesInfoURL() {
		return detailedDirectLossesInfoURL;
	}

	public void setDetailedDirectLossesInfoURL(
			String detailedDirectLossesInfoURL) {
		this.detailedDirectLossesInfoURL = detailedDirectLossesInfoURL;
	}

	public String getIndirectLossesNumber_unit() {
		return indirectLossesNumber_unit;
	}

	public void setIndirectLossesNumber_unit(String indirectLossesNumber_unit) {
		this.indirectLossesNumber_unit = indirectLossesNumber_unit;
	}

	public Double getIndirectLossesNumber() {
		return indirectLossesNumber;
	}

	public void setIndirectLossesNumber(Double indirectLossesNumber) {
		this.indirectLossesNumber = indirectLossesNumber;
	}

	public String getDetailedIndirectLossesInfoURL() {
		return detailedIndirectLossesInfoURL;
	}

	public void setDetailedIndirectLossesInfoURL(
			String detailedIndirectLossesInfoURL) {
		this.detailedIndirectLossesInfoURL = detailedIndirectLossesInfoURL;
	}
}
