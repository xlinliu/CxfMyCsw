package com.event.InnerEntities;

import java.util.Date;

public class AlterInfo {

	private Date alerttime;// �¼�����ʱ��
	private String alertstatus;// �¼�״̬
	private String alertmessagetype;// �¼���Ϣ����
	private String alertscope;// �¼�������Χ
	private DetailAddress alertArea;// �¼���������
	private String alertMessage;// �¼���Ϣ����

	public Date getAlerttime() {
		return alerttime;
	}

	public void setAlerttime(Date alerttime) {
		this.alerttime = alerttime;
	}

	public String getAlertstatus() {
		return alertstatus;
	}

	public void setAlertstatus(String alertstatus) {
		this.alertstatus = alertstatus;
	}

	public String getAlertmessagetype() {
		return alertmessagetype;
	}

	public void setAlertmessagetype(String alertmessagetype) {
		this.alertmessagetype = alertmessagetype;
	}

	public String getAlertscope() {
		return alertscope;
	}

	public void setAlertscope(String alertscope) {
		this.alertscope = alertscope;
	}

	public DetailAddress getAlertArea() {
		return alertArea;
	}

	public void setAlertArea(DetailAddress alertArea) {
		this.alertArea = alertArea;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

}
