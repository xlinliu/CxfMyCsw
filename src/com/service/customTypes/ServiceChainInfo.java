package com.service.customTypes;

import java.util.ArrayList;
import java.util.List;

public class ServiceChainInfo {
	private String docname;
	private String serviceChain_FullName;// ȫ��
	private String serviceChain_ShortName;// ���
	private String serviceChain_Abstract;// ժҪ
	private List<String> serviceChain_keywords = new ArrayList<String>();// �ؼ���
	private String serviceChain_ServiceLinkID;// �����ʶ��
	private List<String> serviceChain_Applications = new ArrayList<String>();// Ԥ��Ӧ��
	private String serviceChain_eventType;// �����¼�������
	private String serviceChain_eventStage;// �����¼��Ľ׶�
	private String serviceChain_ComponentService_ComponentServiceCount;// �����������
	private ComponentServiceType cst = new ComponentServiceType();// ���������Ϣ
	private String serviceChain_Admi_contacts_contact_individualName;// ������ ����
	private String serviceChain_Admi_contacts_contact_OrganName;// ����λ����
	private String serviceChain_Admi_contacts_contact_phone_voice;// ������ �绰����
	private String serviceChain_Admi_contacts_contact_phone_fascimile;// ������
	private String serviceChain_Admi_contacts_contact_address_deliveryPoint;// ��������ϵ��ַ
	private String serviceChain_Admi_contacts_contact_address_city;// ������ ���ڳ���
	private String serviceChain_Admi_contacts_contact_address_administrativeArea;// ��������������
	private String serviceChain_Admi_contacts_contact_address_postalCode;// �������ʱ�
	private String serviceChain_Admi_contacts_contact_address_country;// ������ ����
	private String serviceChain_Admi_contacts_contact_address_electronicMailAddress;// �������ʱ��ַ
	private List<ServiceParamInfo> spi = new ArrayList<ServiceParamInfo>();

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getServiceChain_FullName() {
		return serviceChain_FullName;
	}

	public void setServiceChain_FullName(String serviceChain_FullName) {
		this.serviceChain_FullName = serviceChain_FullName;
	}

	public String getServiceChain_ShortName() {
		return serviceChain_ShortName;
	}

	public void setServiceChain_ShortName(String serviceChain_ShortName) {
		this.serviceChain_ShortName = serviceChain_ShortName;
	}

	public String getServiceChain_Abstract() {
		return serviceChain_Abstract;
	}

	public void setServiceChain_Abstract(String serviceChain_Abstract) {
		this.serviceChain_Abstract = serviceChain_Abstract;
	}

	public List<String> getServiceChain_keywords() {
		return serviceChain_keywords;
	}

	public void setServiceChain_keywords(List<String> serviceChain_keywords) {
		this.serviceChain_keywords = serviceChain_keywords;
	}

	public String getServiceChain_ServiceLinkID() {
		return serviceChain_ServiceLinkID;
	}

	public void setServiceChain_ServiceLinkID(String serviceChain_ServiceLinkID) {
		this.serviceChain_ServiceLinkID = serviceChain_ServiceLinkID;
	}

	public List<String> getServiceChain_Applications() {
		return serviceChain_Applications;
	}

	public void setServiceChain_Applications(
			List<String> serviceChain_Applications) {
		this.serviceChain_Applications = serviceChain_Applications;
	}

	public String getServiceChain_eventType() {
		return serviceChain_eventType;
	}

	public void setServiceChain_eventType(String serviceChain_eventType) {
		this.serviceChain_eventType = serviceChain_eventType;
	}

	public String getServiceChain_eventStage() {
		return serviceChain_eventStage;
	}

	public void setServiceChain_eventStage(String serviceChain_eventStage) {
		this.serviceChain_eventStage = serviceChain_eventStage;
	}

	public String getServiceChain_ComponentService_ComponentServiceCount() {
		return serviceChain_ComponentService_ComponentServiceCount;
	}

	public void setServiceChain_ComponentService_ComponentServiceCount(
			String serviceChain_ComponentService_ComponentServiceCount) {
		this.serviceChain_ComponentService_ComponentServiceCount = serviceChain_ComponentService_ComponentServiceCount;
	}

	public String getServiceChain_Admi_contacts_contact_individualName() {
		return serviceChain_Admi_contacts_contact_individualName;
	}

	public void setServiceChain_Admi_contacts_contact_individualName(
			String serviceChain_Admi_contacts_contact_individualName) {
		this.serviceChain_Admi_contacts_contact_individualName = serviceChain_Admi_contacts_contact_individualName;
	}

	public String getServiceChain_Admi_contacts_contact_OrganName() {
		return serviceChain_Admi_contacts_contact_OrganName;
	}

	public void setServiceChain_Admi_contacts_contact_OrganName(
			String serviceChain_Admi_contacts_contact_OrganName) {
		this.serviceChain_Admi_contacts_contact_OrganName = serviceChain_Admi_contacts_contact_OrganName;
	}

	public String getServiceChain_Admi_contacts_contact_phone_voice() {
		return serviceChain_Admi_contacts_contact_phone_voice;
	}

	public void setServiceChain_Admi_contacts_contact_phone_voice(
			String serviceChain_Admi_contacts_contact_phone_voice) {
		this.serviceChain_Admi_contacts_contact_phone_voice = serviceChain_Admi_contacts_contact_phone_voice;
	}

	public String getServiceChain_Admi_contacts_contact_phone_fascimile() {
		return serviceChain_Admi_contacts_contact_phone_fascimile;
	}

	public void setServiceChain_Admi_contacts_contact_phone_fascimile(
			String serviceChain_Admi_contacts_contact_phone_fascimile) {
		this.serviceChain_Admi_contacts_contact_phone_fascimile = serviceChain_Admi_contacts_contact_phone_fascimile;
	}

	public String getServiceChain_Admi_contacts_contact_address_deliveryPoint() {
		return serviceChain_Admi_contacts_contact_address_deliveryPoint;
	}

	public void setServiceChain_Admi_contacts_contact_address_deliveryPoint(
			String serviceChain_Admi_contacts_contact_address_deliveryPoint) {
		this.serviceChain_Admi_contacts_contact_address_deliveryPoint = serviceChain_Admi_contacts_contact_address_deliveryPoint;
	}

	public String getServiceChain_Admi_contacts_contact_address_city() {
		return serviceChain_Admi_contacts_contact_address_city;
	}

	public void setServiceChain_Admi_contacts_contact_address_city(
			String serviceChain_Admi_contacts_contact_address_city) {
		this.serviceChain_Admi_contacts_contact_address_city = serviceChain_Admi_contacts_contact_address_city;
	}

	public String getServiceChain_Admi_contacts_contact_address_administrativeArea() {
		return serviceChain_Admi_contacts_contact_address_administrativeArea;
	}

	public void setServiceChain_Admi_contacts_contact_address_administrativeArea(
			String serviceChain_Admi_contacts_contact_address_administrativeArea) {
		this.serviceChain_Admi_contacts_contact_address_administrativeArea = serviceChain_Admi_contacts_contact_address_administrativeArea;
	}

	public String getServiceChain_Admi_contacts_contact_address_postalCode() {
		return serviceChain_Admi_contacts_contact_address_postalCode;
	}

	public void setServiceChain_Admi_contacts_contact_address_postalCode(
			String serviceChain_Admi_contacts_contact_address_postalCode) {
		this.serviceChain_Admi_contacts_contact_address_postalCode = serviceChain_Admi_contacts_contact_address_postalCode;
	}

	public String getServiceChain_Admi_contacts_contact_address_country() {
		return serviceChain_Admi_contacts_contact_address_country;
	}

	public void setServiceChain_Admi_contacts_contact_address_country(
			String serviceChain_Admi_contacts_contact_address_country) {
		this.serviceChain_Admi_contacts_contact_address_country = serviceChain_Admi_contacts_contact_address_country;
	}

	public String getServiceChain_Admi_contacts_contact_address_electronicMailAddress() {
		return serviceChain_Admi_contacts_contact_address_electronicMailAddress;
	}

	public void setServiceChain_Admi_contacts_contact_address_electronicMailAddress(
			String serviceChain_Admi_contacts_contact_address_electronicMailAddress) {
		this.serviceChain_Admi_contacts_contact_address_electronicMailAddress = serviceChain_Admi_contacts_contact_address_electronicMailAddress;
	}

	public List<ServiceParamInfo> getSpi() {
		return spi;
	}

	public void setSpi(List<ServiceParamInfo> spi) {
		this.spi = spi;
	}

	public ComponentServiceType getCst() {
		return cst;
	}

	public void setCst(ComponentServiceType cst) {
		this.cst = cst;
	}
}
