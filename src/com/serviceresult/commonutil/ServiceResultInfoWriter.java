package com.serviceresult.commonutil;

import java.util.List;

import com.serviceresult.customTypes.GeoPositionInfo;
import com.serviceresult.customTypes.ServiceResultInfo;
import com.csw.exceptions.DirectoryNotExistException;

/**
 * ��ϵͳ�е�ģ����Ϣ��ȫд�뵽���õ��ࡾModelInfoConfig����
 * 
 * @author yxliang
 * 
 */
public class ServiceResultInfoWriter {
	private static ServiceResultXMLDBUtil mxb = ServiceResultXMLDBUtil
			.getInstance();

	public static void main(String[] args) throws DirectoryNotExistException {
		new ServiceResultInfoWriter();
		Long pre = System.currentTimeMillis();
		ServiceResultInfoWriter.write2ServiceResultInfo();
		for (ServiceResultInfo sr : ServiceResultInfoConfig.getScis()) {
			System.out.println(sr.getDocname());
			System.out.println(sr
					.getServiceResult_Admin_address_administrativeArea());
			System.out.println(sr.getServiceResult_Admin_contact_individual());
		}
		Long now = System.currentTimeMillis();
		System.out.println("cost time: " + (now - pre) + " ms");
	}

	/**
	 * ɾ��ָ��berkeley�е�docname��Ӧ��modelbasicinfo��Ϣ
	 * 
	 * @param docname
	 */
	public static void deleteDocumentFromServiceResultInfo(String docname) {
		for (ServiceResultInfo mbi : ServiceResultInfoConfig.getScis()) {
			if (mbi.getDocname().equals(docname)) {
				ServiceResultInfoConfig.getScis().remove(mbi);
				ServiceResultInfoConfig.getDocnames().remove(docname);
				break;
			}
		}
	}

	/**
	 * ���������ĵ��Ļ�����Ϣ���뵽ModelBasicInfo��
	 * 
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ServiceResultInfo(String docname) {
		ServiceResultInfo mbi = new ServiceResultInfo();
		mbi.setDocname(docname);
		// ��ȡ����ȫ��
		List<String> list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_FullName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_FullName(list.get(0));
		}
		// ��ȡ������
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ShortName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ShortName(list.get(0));
		}
		// ��ȡ����ժҪ
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_Abstract", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_Abstract(list.get(0));
		}
		// ��ȡ����ؼ���
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_Keyword", docname);
		mbi.setServiceResult_Iden_Service_Keywords(list);
		// ��ȡ�����������ı�ʶ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ServiceLinkResultID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ServiceLinkResultID(list.get(0));
		}
		// ��ȡ�������ṩ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ServiceResultProvider", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ServiceResultProvider(list.get(0));
		}
		// ��ȡ����������¼��ı�ʶ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventID(list.get(0));
		}
		// ��ȡ����������¼�������
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventName(list.get(0));
		}
		// ��ȡ����������¼�����
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventType", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventType(list.get(0));
		}
		//�������¼�����ʱ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_timePosition", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventTime(list.get(0));
		}
		
		// ��ȡ����������¼��׶�
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventStage", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventStage(list.get(0));
		}
		// ��ȡ��������ķ������ӱ�ʶ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_serviceLinkID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_serviceLinkID(list.get(0));
		}
		// ��ȡ��������ķ����ʶ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_serviceID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_serviceID(list.get(0));
		}
		// �������Ĳ���ʱ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultTime_timePosition", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_ResultTime_TimeInstant(list.get(0));
		}
		// �������ĸ�ʽResultFormat_Format
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultFormat_Format", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_ResultFormat_format(list.get(0));
		}
		// �������url
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultAccess_Address", docname);
		mbi.getServiceResult_Address_url().addAll(list);
		// ������ϵ��
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_individual(list.get(0));
		}
		// ������ϵ��λ
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_organname(list.get(0));
		}
		// ������ϵ�绰
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_voice(list.get(0));
		}
		// ������ϵ����
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_facsimile", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_facsimile(list.get(0));
		}
		// ������ϵ��ַ
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_deliveryPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_deliveryPoint(list.get(0));
		}
		// ������ϵ����
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_city", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_city(list.get(0));
		}
		// ������ϵ��������
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_administrativeArea(list.get(0));
		}
		// ������ϵ���ڇ���
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_country", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_country(list.get(0));
		}
		// ������ϵ��������
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_postalCode", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_postalcode(list.get(0));
		}
		// ������������
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_electronicMailAddress(list
					.get(0));
		}
		// ���÷����ַ
		writeGeoInfo(mxb, mbi, docname);
		ServiceResultInfoConfig.getScis().add(mbi);
	}

	@SuppressWarnings("unchecked")
	private static void writeGeoInfo(ServiceResultXMLDBUtil mxb2,
			ServiceResultInfo mbi, String docname) {
		GeoPositionInfo gpi = new GeoPositionInfo();
		// ��ȡlower��x��y
		List<String> list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_lowerCorner_longtitude_value", docname);
		if (list != null && list.size() != 0) {
			try {
				gpi.getLowerCorner().setPointx(Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				return;
			}
		}
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_lowerCorner_latitude_value", docname);
		if (list != null && list.size() != 0) {
			try {
				gpi.getLowerCorner().setPointy(Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				return;
			}
		}
		// ��ȡupper��x��y
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_upperCorner_longtitude_value", docname);
		if (list != null && list.size() != 0) {
			try {
				gpi.getUpperCorner().setPointx(Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				return;
			}
		}
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_upperCorner_latitude_value", docname);
		if (list != null && list.size() != 0) {
			try {
				gpi.getUpperCorner().setPointy(Double.parseDouble(list.get(0)));
			} catch (NumberFormatException e) {
				return;
			}
		}
		mbi.setServiceResult_Rela_Event_eventPosition(gpi);
	}

	/**
	 * ����Ϣȫ��д�뵽ModelBasicInfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceResultInfo()
			throws DirectoryNotExistException {
		for (String docname : mxb.getAllDocumentName()) {
			writeDocument2ServiceResultInfo(docname);
		}
	}

	/**
	 * ���ĵ���ȫ������Ϣд�뵽modelinfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceResultName()
			throws DirectoryNotExistException {
		ServiceResultInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
