package com.yxl.mobile.common.types;

import java.util.ArrayList;
import java.util.List;

public class MobileEventBasicInfo {
	private String eventid;// �¼���ʶ��
	private String eventname;// �¼�����
	private String eventtime_begin;// �¼���ʼʱ��
	private String eventtime_end;// �¼�����ʱ��
	private Double eventposition_x;// �¼���������
	private Double eventposition_y;// �¼�����γ��
	private String eventType;// �¼�����
	private String eventperiod;// �¼����ڽ׶�
	private String eventprocessStatus;// �¼�����״̬
	private List<EventRelatedServiceResult> ersrs = new ArrayList<EventRelatedServiceResult>();

	public String getEventid() {
		return eventid;
	}

	public String getEventprocessStatus() {
		return eventprocessStatus;
	}

	public void setEventprocessStatus(String eventprocessStatus) {
		this.eventprocessStatus = eventprocessStatus;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public Double getEventposition_x() {
		return eventposition_x;
	}

	public void setEventposition_x(Double eventposition_x) {
		this.eventposition_x = eventposition_x;
	}

	public Double getEventposition_y() {
		return eventposition_y;
	}

	public void setEventposition_y(Double eventposition_y) {
		this.eventposition_y = eventposition_y;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventtime_begin() {
		return eventtime_begin;
	}

	public void setEventtime_begin(String eventtime_begin) {
		this.eventtime_begin = eventtime_begin;
	}

	public String getEventtime_end() {
		return eventtime_end;
	}

	public void setEventtime_end(String eventtime_end) {
		this.eventtime_end = eventtime_end;
	}

	public List<EventRelatedServiceResult> getErsrs() {
		return ersrs;
	}

	public void setErsrs(List<EventRelatedServiceResult> ersrs) {
		this.ersrs = ersrs;
	}

	public String getEventperiod() {
		return eventperiod;
	}

	public void setEventperiod(String eventperiod) {
		this.eventperiod = eventperiod;
	}

}
