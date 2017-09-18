package com.service.commonutil;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.csw.exceptions.DirectoryNotExistException;
import com.service.customTypes.AutomProcessType;
import com.service.customTypes.ChainFlowType;
import com.service.customTypes.ComplexParamType;
import com.service.customTypes.ComplexProcess;
import com.service.customTypes.ComponentServiceType;
import com.service.customTypes.DataFigureType;
import com.service.customTypes.Figures;
import com.service.customTypes.FlowFigureType;
import com.service.customTypes.LiteralParamType;
import com.service.customTypes.OutputParamType;
import com.service.customTypes.ProcessFigureType;
import com.service.customTypes.ServiceChainInfo;

/**
 * 将系统中的模型信息完全写入到配置的类【ModelInfoConfig】中
 * 
 * @author yxliang
 * 
 */
public class ServiceChainlInfoWriter {
	private static ServiceChainXMLDBUtil mxb = ServiceChainXMLDBUtil
			.getInstance();
	/**
	 * 删除指定berkeley中的docname对应的modelbasicinfo信息
	 * 
	 * @param docname
	 */
	public static void deleteDocumentFromServiceChainInfo(String docname) {
		for (ServiceChainInfo mbi : ServiceChainInfoConfig.getScis()) {
			if (mbi.getDocname().equals(docname)) {
				ServiceChainInfoConfig.getScis().remove(mbi);
				ServiceChainInfoConfig.getDocnames().remove(docname);
				break;
			}
		}
	}

	/**
	 * 将新增的文档的基本信息加入到ModelBasicInfo中
	 * 
	 * @param docname
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ServiceChainInfo(String docname)
			throws DocumentException {
		ServiceChainInfo mbi = new ServiceChainInfo();
		mbi.setDocname(docname);
		// 获取服务全称
		List<String> list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_FullName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_FullName(list.get(0));
		}
		// 获取服务简称
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ShortName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_ShortName(list.get(0));
		}
		// 获取服务摘要
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Abstract", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Abstract(list.get(0));
		}
		// 获取服务关键字serviceChain_keywords
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_keyword", docname);
		mbi.getServiceChain_keywords().addAll(list);
		// 服务标志符
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ServiceLinkID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_ServiceLinkID(list.get(0));
		}
		// 服务预期应用
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Application", docname);
		mbi.getServiceChain_Applications().addAll(list);
		// 关联服务事件类型
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_eventType", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_eventType(list.get(0));
		}
		System.out.println(list);
		// 关联服务事件阶段
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_eventStage", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_eventStage(list.get(0));
		}
		System.out.println(list);
		// 服务组件个数
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ComponentService_ComponentServiceCount", docname);
		if (list != null && list.size() != 0) {
			try {
				mbi.setServiceChain_ComponentService_ComponentServiceCount(list
						.get(0) + "");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// 负责人名称
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_individualName(list
					.get(0));
		}
		// 负责人单位
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_OrganName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_OrganName(list.get(0));
		}
		// 负责人电话
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_phone_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_phone_voice(list.get(0));
		}
		// 负责人传真
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_phone_fascimile", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_phone_fascimile(list
					.get(0));
		}
		// 负责人联系地址
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_deliveryPoint(list
					.get(0));
		}
		// 负责人所在城市
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_city", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_city(list.get(0));
		}
		// 负责人所在行政区域
		list = (List<String>) mxb
				.queryDocumentOfServiceChain(
						"serviceChain_Admi_contacts_contact_address_administrativeArea",
						docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_administrativeArea(list
					.get(0));
		}
		// 负责人邮编
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_postalCode(list
					.get(0));
		}
		// 负责人国家
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_country", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_country(list
					.get(0));
		}
		// 负责人联系电子邮件
		list = (List<String>) mxb
				.queryDocumentOfServiceChain(
						"serviceChain_Admi_contacts_contact_address_electronicMailAddress",
						docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_electronicMailAddress(list
					.get(0));
		}
		ComponentServiceType cst = new ComponentServiceType();
		// 组件服务的复杂处理过程
		list = (List<String>) mxb
				.queryDocumentOfServiceChain(
						"serviceChain_ComponentService_ComponentServices_ComplexProcess",
						docname);
		// System.out.println(list);
		if (list != null && list.size() != 0) {
			ComplexProcess cp = cst.getCpp();
			Document document = DocumentHelper.parseText(list.get(0));
			Element RootElement = document.getRootElement();
			String uuid = RootElement.attributeValue("uuid");
			cp.setUuid(uuid);
			Iterator<Element> elements = RootElement
					.elementIterator("AtomProcess");
			while (elements.hasNext()) {
				AutomProcessType apt = new AutomProcessType();
				Element e = elements.next();
				apt.setUuid(e.attributeValue("uuid"));
				apt.setName(e.attributeValue("name"));
				apt.setXmlabstract(e.attributeValue("abstract"));
				Iterator<Element> tempelements = e.elementIterator();
				while (tempelements.hasNext()) {
					Element element = tempelements.next();
					if (element.getName().equals("ComplexParam")) {
						ComplexParamType cpt = new ComplexParamType();
						cpt.setFormat(element.attributeValue("format"));
						cpt.setName(element.attributeValue("name"));
						cpt.setValue(element.attributeValue("value"));
						apt.getCpt().add(cpt);
					} else if (element.getName().equals("LiteralParam")) {
						LiteralParamType lpt = new LiteralParamType();
						lpt.setFormat(element.attributeValue("format"));
						lpt.setName(element.attributeValue("name"));
						lpt.setValue(element.attributeValue("value"));
						apt.getLpt().add(lpt);
					} else if (element.getName().equals("OutPutParam")) {
						OutputParamType lpt = new OutputParamType();
						lpt.setFormat(element.attributeValue("format"));
						lpt.setName(element.attributeValue("name"));
						lpt.setValue(element.attributeValue("value"));
						apt.getOpt().add(lpt);
					}
				}
				cp.getAp().add(apt);
			}
			elements = null;
			elements = RootElement.elementIterator("ChainFlow");
			while (elements.hasNext()) {
				ChainFlowType cft = new ChainFlowType();
				cft.setUuid(elements.next().attributeValue("uuid"));
				// 加入到其中
				cp.getCft().add(cft);
			}
			cst.setCpp(cp);
		}
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ComponentService_ComponentServices_Figures",
				docname);
		if (list != null && list.size() != 0) {
			Figures fi = cst.getFigrues();
			Document document = DocumentHelper.parseText(list.get(0));
			Element RootElement = document.getRootElement();
			Iterator<Element> eli = RootElement.elementIterator();
			while (eli.hasNext()) {
				Element e = eli.next();
				if (e.getName().equals("ProcessFigure")) {
					ProcessFigureType pft = new ProcessFigureType();
					pft.setUuid(e.attributeValue("uuid"));
					pft.setLocXY(e.attributeValue("locXY"));
					Iterator<Element> teme = e.elementIterator();
					while (teme.hasNext()) {
						Element te = teme.next();
						DataFigureType dft = new DataFigureType();
						dft.setUuid(te.attributeValue("uuid"));
						dft.setLocXY(te.attributeValue("locXY"));
						dft.setState(te.attributeValue("state"));
						pft.getDft().add(dft);
					}
					fi.getPft().add(pft);
				} else if (e.getName().equals("FlowFigure")) {
					FlowFigureType fft = new FlowFigureType();
					fft.setUuid(e.attributeValue("uuid"));
					fi.getFft().add(fft);
				}
			}
			cst.setFigrues(fi);
		}
		mbi.setCst(cst);
		ServiceChainInfoConfig.getScis().add(mbi);
		ServiceChainInfoConfig.getDocnames().add(docname);
	}

	/**
	 * 将信息全部写入到ModelBasicInfo中
	 * 
	 * @throws DocumentException
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceChainInfo() throws DocumentException,
			DirectoryNotExistException {
		for (String docname : mxb.getAllDocumentName()) {
			writeDocument2ServiceChainInfo(docname);
		}
	}

	/**
	 * 将文档中全部的信息写入到modelinfo中
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceChainName()
			throws DirectoryNotExistException {
		ServiceChainInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
