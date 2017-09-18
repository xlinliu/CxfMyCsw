package com.event.content.config;

public class SystemConfig {
	public final static String EMLURL = "http://www.swevent.net/emlEvent/1.0";
	public final static String SWEURL = "http://www.opengis.net/swe/1.0";
	public final static String SMLURL = "http://www.opengis.net/sensorML/1.0";
	public final static String GMLURL = "http://www.opengis.net/gml";
	// ClassificationRequireStr�ֶ��а�����ֵ����classification�б�����ֶ�����
	public final static String ClassificationRequireStr = "eventCategory,eventPattern,eventInheritance,eventUrgency,eventSeverity,eventCertainty";
	// ContactInfo�е�address�е���������ֶ�����
	public final static String ContactAddressRequireStr = "deliveryPoint,city,administrativeArea,postalCode,country,electronicMailAddress";

	public final static String TIMEGMLFORMAT = "yyyy-mm-dd hh:mm:ss";
}
