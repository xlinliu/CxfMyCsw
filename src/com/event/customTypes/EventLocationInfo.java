package com.event.customTypes;

public class EventLocationInfo {
	private Boolean eventlocationfixed;// 事件位置是否固定
	private String eventlocationreferenceFrame;// 事件位置坐标系统
	private Double eventposition_y;// 事件位置的纬度
	private String eventposition_y_unit;// 事件位置的纬度的单位
	private Double eventposition_x;// 事件位置的经度
	private String eventposition_x_unit;// 事件位置的经度的单位
	private Double eventposition_z;// 事件位置的高度
	private String eventposition_z_unit;// 事件位置的高度的单位

	public Boolean getEventlocationfixed() {
		return eventlocationfixed;
	}

	public void setEventlocationfixed(Boolean eventlocationfixed) {
		this.eventlocationfixed = eventlocationfixed;
	}

	public String getEventlocationreferenceFrame() {
		return eventlocationreferenceFrame;
	}

	public void setEventlocationreferenceFrame(
			String eventlocationreferenceFrame) {
		this.eventlocationreferenceFrame = eventlocationreferenceFrame;
	}

	public Double getEventposition_y() {
		return eventposition_y;
	}

	public void setEventposition_y(Double eventposition_y) {
		this.eventposition_y = eventposition_y;
	}

	public String getEventposition_y_unit() {
		return eventposition_y_unit;
	}

	public void setEventposition_y_unit(String eventposition_y_unit) {
		this.eventposition_y_unit = eventposition_y_unit;
	}

	public Double getEventposition_x() {
		return eventposition_x;
	}

	public void setEventposition_x(Double eventposition_x) {
		this.eventposition_x = eventposition_x;
	}

	public String getEventposition_x_unit() {
		return eventposition_x_unit;
	}

	public void setEventposition_x_unit(String eventposition_x_unit) {
		this.eventposition_x_unit = eventposition_x_unit;
	}

	public Double getEventposition_z() {
		return eventposition_z;
	}

	public void setEventposition_z(Double eventposition_z) {
		this.eventposition_z = eventposition_z;
	}

	public String getEventposition_z_unit() {
		return eventposition_z_unit;
	}

	public void setEventposition_z_unit(String eventposition_z_unit) {
		this.eventposition_z_unit = eventposition_z_unit;
	}
}
