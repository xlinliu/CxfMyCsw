package com.serviceresult.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ServiceResultInfo {
	private String docname;
	private String serviceResult_Iden_Service_FullName;// 服务结果的全称
	private String serviceResult_Iden_Service_ShortName;// 服务结果的简称
	private String serviceResult_Iden_Service_Abstract;// 服务结果的摘要
	private List<String> serviceResult_Iden_Service_Keywords = new ArrayList<String>();// 服务结果关键字
	private String serviceResult_Iden_Service_ServiceLinkResultID;// 服务结果的标识符
	private String serviceResult_Iden_Service_ServiceResultProvider;// 服务结果提供者
	private String serviceResult_Rela_Event_eventID;// 服务关联事件的事件标识符。
	private String serviceResult_Rela_Event_eventName;// 服务关联事件的事件名称
	private String serviceResult_Rela_Event_eventTime;// 服务关联事件的事件时间
	private GeoPositionInfo serviceResult_Rela_Event_eventPosition = new GeoPositionInfo();// 设置服务绑定的事件地址
	private String serviceResult_Rela_Event_eventType;// 服务绑定的事件的类型
	private String serviceResult_Rela_Event_eventStage;// 服务绑定的事件的阶段
	private String serviceResult_Rela_serviceLinkID;// 事件相关的服务标识符
	private String serviceResult_Rela_serviceID;// 事件相关的服务标识符
	private String serviceResult_ResultTime_TimeInstant;// 事件发生时间
	private String serviceResult_ResultFormat_format;// 事件结果格式
	private List<String> serviceResult_Address_url = new ArrayList<String>();// 服务结果处理
	private String serviceResult_Admin_contact_individual;// 服务维护个人名称
	private String serviceResult_Admin_contact_organname;// 服务单位名称
	private String serviceResult_Admin_contact_voice;// 服务单位电话
	private String serviceResult_Admin_contact_facsimile;// 服务单位传真
	private String serviceResult_Admin_address_deliveryPoint;// 服务单位地址
	private String serviceResult_Admin_address_city;// 服务单位城市
	private String serviceResult_Admin_address_postalcode;// 服务邮政编码
	private String serviceResult_Admin_address_country;// 服务国家
	private String serviceResult_Admin_address_administrativeArea;// 服务行政区域
	private String serviceResult_Admin_address_electronicMailAddress;// 服务电子邮件

	public String getServiceResult_Admin_address_administrativeArea() {
		return serviceResult_Admin_address_administrativeArea;
	}

	public void setServiceResult_Admin_address_administrativeArea(
			String serviceResult_Admin_address_administrativeArea) {
		this.serviceResult_Admin_address_administrativeArea = serviceResult_Admin_address_administrativeArea;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getServiceResult_Iden_Service_FullName() {
		return serviceResult_Iden_Service_FullName;
	}

	public void setServiceResult_Iden_Service_FullName(
			String serviceResult_Iden_Service_FullName) {
		this.serviceResult_Iden_Service_FullName = serviceResult_Iden_Service_FullName;
	}

	public String getServiceResult_Iden_Service_ShortName() {
		return serviceResult_Iden_Service_ShortName;
	}

	public void setServiceResult_Iden_Service_ShortName(
			String serviceResult_Iden_Service_ShortName) {
		this.serviceResult_Iden_Service_ShortName = serviceResult_Iden_Service_ShortName;
	}

	public String getServiceResult_Iden_Service_Abstract() {
		return serviceResult_Iden_Service_Abstract;
	}

	public void setServiceResult_Iden_Service_Abstract(
			String serviceResult_Iden_Service_Abstract) {
		this.serviceResult_Iden_Service_Abstract = serviceResult_Iden_Service_Abstract;
	}

	public List<String> getServiceResult_Iden_Service_Keywords() {
		return serviceResult_Iden_Service_Keywords;
	}

	public void setServiceResult_Iden_Service_Keywords(
			List<String> serviceResult_Iden_Service_Keywords) {
		this.serviceResult_Iden_Service_Keywords = serviceResult_Iden_Service_Keywords;
	}

	public String getServiceResult_Iden_Service_ServiceLinkResultID() {
		return serviceResult_Iden_Service_ServiceLinkResultID;
	}

	public void setServiceResult_Iden_Service_ServiceLinkResultID(
			String serviceResult_Iden_Service_ServiceLinkResultID) {
		this.serviceResult_Iden_Service_ServiceLinkResultID = serviceResult_Iden_Service_ServiceLinkResultID;
	}

	public String getServiceResult_Iden_Service_ServiceResultProvider() {
		return serviceResult_Iden_Service_ServiceResultProvider;
	}

	public void setServiceResult_Iden_Service_ServiceResultProvider(
			String serviceResult_Iden_Service_ServiceResultProvider) {
		this.serviceResult_Iden_Service_ServiceResultProvider = serviceResult_Iden_Service_ServiceResultProvider;
	}

	public String getServiceResult_Rela_Event_eventID() {
		return serviceResult_Rela_Event_eventID;
	}

	public void setServiceResult_Rela_Event_eventID(
			String serviceResult_Rela_Event_eventID) {
		this.serviceResult_Rela_Event_eventID = serviceResult_Rela_Event_eventID;
	}

	public String getServiceResult_Rela_Event_eventName() {
		return serviceResult_Rela_Event_eventName;
	}

	public void setServiceResult_Rela_Event_eventName(
			String serviceResult_Rela_Event_eventName) {
		this.serviceResult_Rela_Event_eventName = serviceResult_Rela_Event_eventName;
	}

	public String getServiceResult_Rela_Event_eventTime() {
		return serviceResult_Rela_Event_eventTime;
	}

	public void setServiceResult_Rela_Event_eventTime(
			String serviceResult_Rela_Event_eventTime) {
		this.serviceResult_Rela_Event_eventTime = serviceResult_Rela_Event_eventTime;
	}

	public GeoPositionInfo getServiceResult_Rela_Event_eventPosition() {
		return serviceResult_Rela_Event_eventPosition;
	}

	public void setServiceResult_Rela_Event_eventPosition(
			GeoPositionInfo serviceResult_Rela_Event_eventPosition) {
		this.serviceResult_Rela_Event_eventPosition = serviceResult_Rela_Event_eventPosition;
	}

	public String getServiceResult_Rela_Event_eventType() {
		return serviceResult_Rela_Event_eventType;
	}

	public void setServiceResult_Rela_Event_eventType(
			String serviceResult_Rela_Event_eventType) {
		this.serviceResult_Rela_Event_eventType = serviceResult_Rela_Event_eventType;
	}

	public String getServiceResult_Rela_Event_eventStage() {
		return serviceResult_Rela_Event_eventStage;
	}

	public void setServiceResult_Rela_Event_eventStage(
			String serviceResult_Rela_Event_eventStage) {
		this.serviceResult_Rela_Event_eventStage = serviceResult_Rela_Event_eventStage;
	}

	public String getServiceResult_Rela_serviceLinkID() {
		return serviceResult_Rela_serviceLinkID;
	}

	public void setServiceResult_Rela_serviceLinkID(
			String serviceResult_Rela_serviceLinkID) {
		this.serviceResult_Rela_serviceLinkID = serviceResult_Rela_serviceLinkID;
	}

	public String getServiceResult_Rela_serviceID() {
		return serviceResult_Rela_serviceID;
	}

	public void setServiceResult_Rela_serviceID(
			String serviceResult_Rela_serviceID) {
		this.serviceResult_Rela_serviceID = serviceResult_Rela_serviceID;
	}

	public String getServiceResult_ResultTime_TimeInstant() {
		return serviceResult_ResultTime_TimeInstant;
	}

	public void setServiceResult_ResultTime_TimeInstant(
			String serviceResult_ResultTime_TimeInstant) {
		this.serviceResult_ResultTime_TimeInstant = serviceResult_ResultTime_TimeInstant;
	}

	public String getServiceResult_ResultFormat_format() {
		return serviceResult_ResultFormat_format;
	}

	public void setServiceResult_ResultFormat_format(
			String serviceResult_ResultFormat_format) {
		this.serviceResult_ResultFormat_format = serviceResult_ResultFormat_format;
	}

	public List<String> getServiceResult_Address_url() {
		return serviceResult_Address_url;
	}

	public void setServiceResult_Address_url(
			List<String> serviceResult_Address_url) {
		this.serviceResult_Address_url = serviceResult_Address_url;
	}

	public String getServiceResult_Admin_contact_individual() {
		return serviceResult_Admin_contact_individual;
	}

	public void setServiceResult_Admin_contact_individual(
			String serviceResult_Admin_contact_individual) {
		this.serviceResult_Admin_contact_individual = serviceResult_Admin_contact_individual;
	}

	public String getServiceResult_Admin_contact_organname() {
		return serviceResult_Admin_contact_organname;
	}

	public void setServiceResult_Admin_contact_organname(
			String serviceResult_Admin_contact_organname) {
		this.serviceResult_Admin_contact_organname = serviceResult_Admin_contact_organname;
	}

	public String getServiceResult_Admin_contact_voice() {
		return serviceResult_Admin_contact_voice;
	}

	public void setServiceResult_Admin_contact_voice(
			String serviceResult_Admin_contact_voice) {
		this.serviceResult_Admin_contact_voice = serviceResult_Admin_contact_voice;
	}

	public String getServiceResult_Admin_contact_facsimile() {
		return serviceResult_Admin_contact_facsimile;
	}

	public void setServiceResult_Admin_contact_facsimile(
			String serviceResult_Admin_contact_facsimile) {
		this.serviceResult_Admin_contact_facsimile = serviceResult_Admin_contact_facsimile;
	}

	public String getServiceResult_Admin_address_deliveryPoint() {
		return serviceResult_Admin_address_deliveryPoint;
	}

	public void setServiceResult_Admin_address_deliveryPoint(
			String serviceResult_Admin_address_deliveryPoint) {
		this.serviceResult_Admin_address_deliveryPoint = serviceResult_Admin_address_deliveryPoint;
	}

	public String getServiceResult_Admin_address_city() {
		return serviceResult_Admin_address_city;
	}

	public void setServiceResult_Admin_address_city(
			String serviceResult_Admin_address_city) {
		this.serviceResult_Admin_address_city = serviceResult_Admin_address_city;
	}

	public String getServiceResult_Admin_address_postalcode() {
		return serviceResult_Admin_address_postalcode;
	}

	public void setServiceResult_Admin_address_postalcode(
			String serviceResult_Admin_address_postalcode) {
		this.serviceResult_Admin_address_postalcode = serviceResult_Admin_address_postalcode;
	}

	public String getServiceResult_Admin_address_country() {
		return serviceResult_Admin_address_country;
	}

	public void setServiceResult_Admin_address_country(
			String serviceResult_Admin_address_country) {
		this.serviceResult_Admin_address_country = serviceResult_Admin_address_country;
	}

	public String getServiceResult_Admin_address_electronicMailAddress() {
		return serviceResult_Admin_address_electronicMailAddress;
	}

	public void setServiceResult_Admin_address_electronicMailAddress(
			String serviceResult_Admin_address_electronicMailAddress) {
		this.serviceResult_Admin_address_electronicMailAddress = serviceResult_Admin_address_electronicMailAddress;
	}

}
