package com.event.JavaBeans;

import com.event.InnerEntities.EventClassification;

/**
 * ��Ҫ�Ǵ洢�¼��ķ�����Ϣ
 * 
 * @author yxliang
 * 
 */
public class EventClassificationJBean extends EventClassification {

	private int classificationid;// �¼�����ı��
	private String eventid;// �¼��ı��

	public int getClassificationid() {
		return classificationid;
	}

	public void setClassificationid(int classificationid) {
		this.classificationid = classificationid;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

}
