package com.event.JavaBeans;

import java.util.Date;

/**
 * ������Ҫ������¼��Ļ�����Ϣ��������ʶ��Ϣ��������Ϣ��ʱ����Ϣ�Ĵ洢
 * 
 * @author yxliang
 * 
 */
public class EventIdentificationJBean {
	private String eventid;// �¼��ı��
	private String userid;// �¼��ϴ��ߵı��
	private Date uploadtime;// �¼��ϴ���ʱ��
	private String eventname;// �¼�������
	private String eventdesc;// �¼�������
	private EventSpaceTimeJBean estj;// �¼���ʱ����Ϣ
	private EventClassificationJBean ecjb;// �¼��ķ�����Ϣ
	private String eventPhases;// �¼��洢���¼��ĸ��׶ε���Ϣ,��1��4λ���ֱ���precaution,preparation,response��recovery��1����ʾ��Ӧ����Ϣ���ڣ�0����ʾ��Ӧ����Ϣ������

	public String getEventid() {
		return eventid;
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

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}

	public EventSpaceTimeJBean getEstj() {
		return estj;
	}

	public void setEstj(EventSpaceTimeJBean estj) {
		this.estj = estj;
	}

	public EventClassificationJBean getEcjb() {
		return ecjb;
	}

	public void setEcjb(EventClassificationJBean ecjb) {
		this.ecjb = ecjb;
	}

	public String getEventPhases() {
		return eventPhases;
	}

	public void setEventPhases(String eventPhases) {
		this.eventPhases = eventPhases;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

}
