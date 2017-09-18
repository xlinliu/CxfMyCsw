package com.event.customTypes;

public class EventstateReportInfo {
	private String repairedRoadsURL;// 修复道路详细url地址
	private String searchedRegionsURL;// 查询区域的url地址
	private String repairedFortificationsURL;// 修复公事url地址

	public String getRepairedRoadsURL() {
		return repairedRoadsURL;
	}

	public void setRepairedRoadsURL(String repairedRoadsURL) {
		this.repairedRoadsURL = repairedRoadsURL;
	}

	public String getSearchedRegionsURL() {
		return searchedRegionsURL;
	}

	public void setSearchedRegionsURL(String searchedRegionsURL) {
		this.searchedRegionsURL = searchedRegionsURL;
	}

	public String getRepairedFortificationsURL() {
		return repairedFortificationsURL;
	}

	public void setRepairedFortificationsURL(String repairedFortificationsURL) {
		this.repairedFortificationsURL = repairedFortificationsURL;
	}
}
