package com.serviceresult.commonutil;

import java.util.List;

import com.serviceresult.customTypes.GeoPositionInfo;
import com.serviceresult.customTypes.ServiceResultInfo;
import com.csw.exceptions.DirectoryNotExistException;

/**
 * 将系统中的模型信息完全写入到配置的类【ModelInfoConfig】中
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
	 * 删除指定berkeley中的docname对应的modelbasicinfo信息
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
	 * 将新增的文档的基本信息加入到ModelBasicInfo中
	 * 
	 * @param docname
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ServiceResultInfo(String docname) {
		ServiceResultInfo mbi = new ServiceResultInfo();
		mbi.setDocname(docname);
		// 获取服务全称
		List<String> list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_FullName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_FullName(list.get(0));
		}
		// 获取服务简称
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ShortName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ShortName(list.get(0));
		}
		// 获取服务摘要
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_Abstract", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_Abstract(list.get(0));
		}
		// 获取服务关键字
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_Keyword", docname);
		mbi.setServiceResult_Iden_Service_Keywords(list);
		// 获取服务关联结果的标识符
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ServiceLinkResultID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ServiceLinkResultID(list.get(0));
		}
		// 获取服务结果提供者
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Identification_ServiceResultProvider", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Iden_Service_ServiceResultProvider(list.get(0));
		}
		// 获取结果关联的事件的标识符
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventID(list.get(0));
		}
		// 获取结果关联的事件的名称
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventName(list.get(0));
		}
		// 获取结果关联的事件类型
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventType", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventType(list.get(0));
		}
		//关联的事件发生时间
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_timePosition", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventTime(list.get(0));
		}
		
		// 获取结果关联的事件阶段
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_eventStage", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_Event_eventStage(list.get(0));
		}
		// 获取结果关联的服务连接标识符
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_serviceLinkID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_serviceLinkID(list.get(0));
		}
		// 获取结果关联的服务标识符
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"RelationInfo_serviceID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Rela_serviceID(list.get(0));
		}
		// 服务结果的产生时间
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultTime_timePosition", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_ResultTime_TimeInstant(list.get(0));
		}
		// 服务结果的格式ResultFormat_Format
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultFormat_Format", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_ResultFormat_format(list.get(0));
		}
		// 服务调用url
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"ResultAccess_Address", docname);
		mbi.getServiceResult_Address_url().addAll(list);
		// 服务联系人
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_individual(list.get(0));
		}
		// 服务联系单位
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_organizationName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_organname(list.get(0));
		}
		// 服务联系电话
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_voice(list.get(0));
		}
		// 服务联系传真
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_facsimile", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_contact_facsimile(list.get(0));
		}
		// 服务联系地址
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_deliveryPoint", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_deliveryPoint(list.get(0));
		}
		// 服务联系城市
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_city", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_city(list.get(0));
		}
		// 服务联系行政区域
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_administrativeArea", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_administrativeArea(list.get(0));
		}
		// 服务联系所在國家
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_country", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_country(list.get(0));
		}
		// 服务联系邮政编码
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_postalCode", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_postalcode(list.get(0));
		}
		// 服务邮政编码
		list = (List<String>) mxb.queryDocumentOfServiceResult(
				"Administration_contact_electronicMailAddress", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceResult_Admin_address_electronicMailAddress(list
					.get(0));
		}
		// 设置服务地址
		writeGeoInfo(mxb, mbi, docname);
		ServiceResultInfoConfig.getScis().add(mbi);
	}

	@SuppressWarnings("unchecked")
	private static void writeGeoInfo(ServiceResultXMLDBUtil mxb2,
			ServiceResultInfo mbi, String docname) {
		GeoPositionInfo gpi = new GeoPositionInfo();
		// 获取lower的x，y
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
		// 获取upper的x，y
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
	 * 将信息全部写入到ModelBasicInfo中
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
	 * 将文档中全部的信息写入到modelinfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceResultName()
			throws DirectoryNotExistException {
		ServiceResultInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
