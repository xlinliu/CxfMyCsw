package com.event.JavaBeans;

import com.event.InnerEntities.EventClassification;

/**
 * 主要是存储事件的分类信息
 * 
 * @author yxliang
 * 
 */
public class EventClassificationJBean extends EventClassification {

	private int classificationid;// 事件分类的标号
	private String eventid;// 事件的编号

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
