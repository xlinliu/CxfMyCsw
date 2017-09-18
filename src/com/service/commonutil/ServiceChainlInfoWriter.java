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
 * ��ϵͳ�е�ģ����Ϣ��ȫд�뵽���õ��ࡾModelInfoConfig����
 * 
 * @author yxliang
 * 
 */
public class ServiceChainlInfoWriter {
	private static ServiceChainXMLDBUtil mxb = ServiceChainXMLDBUtil
			.getInstance();
	/**
	 * ɾ��ָ��berkeley�е�docname��Ӧ��modelbasicinfo��Ϣ
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
	 * ���������ĵ��Ļ�����Ϣ���뵽ModelBasicInfo��
	 * 
	 * @param docname
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void writeDocument2ServiceChainInfo(String docname)
			throws DocumentException {
		ServiceChainInfo mbi = new ServiceChainInfo();
		mbi.setDocname(docname);
		// ��ȡ����ȫ��
		List<String> list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_FullName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_FullName(list.get(0));
		}
		// ��ȡ������
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ShortName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_ShortName(list.get(0));
		}
		// ��ȡ����ժҪ
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Abstract", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Abstract(list.get(0));
		}
		// ��ȡ����ؼ���serviceChain_keywords
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_keyword", docname);
		mbi.getServiceChain_keywords().addAll(list);
		// �����־��
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_ServiceLinkID", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_ServiceLinkID(list.get(0));
		}
		// ����Ԥ��Ӧ��
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Application", docname);
		mbi.getServiceChain_Applications().addAll(list);
		// ���������¼�����
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_eventType", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_eventType(list.get(0));
		}
		System.out.println(list);
		// ���������¼��׶�
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_eventStage", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_eventStage(list.get(0));
		}
		System.out.println(list);
		// �����������
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
		// ����������
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_individualName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_individualName(list
					.get(0));
		}
		// �����˵�λ
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_OrganName", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_OrganName(list.get(0));
		}
		// �����˵绰
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_phone_voice", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_phone_voice(list.get(0));
		}
		// �����˴���
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_phone_fascimile", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_phone_fascimile(list
					.get(0));
		}
		// ��������ϵ��ַ
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_deliveryPoint",
				docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_deliveryPoint(list
					.get(0));
		}
		// ���������ڳ���
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_city", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_city(list.get(0));
		}
		// ������������������
		list = (List<String>) mxb
				.queryDocumentOfServiceChain(
						"serviceChain_Admi_contacts_contact_address_administrativeArea",
						docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_administrativeArea(list
					.get(0));
		}
		// �������ʱ�
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_postalCode",
				docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_postalCode(list
					.get(0));
		}
		// �����˹���
		list = (List<String>) mxb.queryDocumentOfServiceChain(
				"serviceChain_Admi_contacts_contact_address_country", docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_country(list
					.get(0));
		}
		// ��������ϵ�����ʼ�
		list = (List<String>) mxb
				.queryDocumentOfServiceChain(
						"serviceChain_Admi_contacts_contact_address_electronicMailAddress",
						docname);
		if (list != null && list.size() != 0) {
			mbi.setServiceChain_Admi_contacts_contact_address_electronicMailAddress(list
					.get(0));
		}
		ComponentServiceType cst = new ComponentServiceType();
		// �������ĸ��Ӵ������
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
				// ���뵽����
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
	 * ����Ϣȫ��д�뵽ModelBasicInfo��
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
	 * ���ĵ���ȫ������Ϣд�뵽modelinfo��
	 * 
	 * @throws DirectoryNotExistException
	 */
	public static void write2ServiceChainName()
			throws DirectoryNotExistException {
		ServiceChainInfoConfig.getDocnames().addAll(mxb.getAllDocumentName());
	}
}
