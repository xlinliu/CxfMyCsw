package com.event.commonutils;

import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.XMLnotFormatException;

/**
 * ���¼������ĵ����д���
 * 
 * @author yxliang
 * 
 */
public class EventCommonXMLUtil {

	/**
	 * ��preparationxml���ֲ��뵽xmlcontent��
	 * 
	 * @param xmlcontent
	 * @param preparationxml
	 * @throws XMLnotFormatException
	 */
	public static String insertPreparationPhase(String xmlcontent,
			String preparationxml) throws XMLnotFormatException {
		return insertBasicPhase(xmlcontent, preparationxml, "Preparation");
	}

	/**
	 * ��responsexml���ֲ��뵽xmlcontent��
	 * 
	 * @param xmlcontent
	 * @param preparationxml
	 * @throws XMLnotFormatException
	 */
	public static String insertResponsePhase(String xmlcontent,
			String responsexml) throws XMLnotFormatException {
		return insertBasicPhase(xmlcontent, responsexml, "Response");
	}

	/**
	 * ��responsexml���ֲ��뵽xmlcontent��
	 * 
	 * @param xmlcontent
	 * @param preparationxml
	 * @throws XMLnotFormatException
	 */
	public static String insertRecoveryPhase(String xmlcontent,
			String recoveryxml) throws XMLnotFormatException {
		return insertBasicPhase(xmlcontent, recoveryxml, "Recovery");
	}

	/**
	 * ��event�Ĳ�����Ϣ���뵽������xml�ĵ���
	 * 
	 * @param xmlcontent
	 * @param partxml
	 * @param pahseStr
	 *            ��Ҫ���ӵĽ׶�
	 * @return �������Ϻ�����EventAttribute�� ���ĵ������ݣ���������쳣���򷵻�xmlcontent
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static String insertBasicPhase(String xmlcontent, String partxml,
			String pahseStr) throws XMLnotFormatException {
		Document xmlcontentdoc = null;
		Document partxmldoc = null;
		try {
			xmlcontentdoc = DocumentHelper.parseText(xmlcontent); // ���ַ���תΪXML
			partxmldoc = DocumentHelper.parseText(partxml);
			Element testelement = partxmldoc.getRootElement().element(
					"eventPeriod");
			if (testelement == null) {
				throw new XMLnotFormatException("���ݵ�xml�ĵ����淶!");
			} else {
				if (!testelement.getText().equals(pahseStr)) {
					throw new XMLnotFormatException("���ݵ�xml�ĵ������¼�[" + pahseStr
							+ "]�׶εĹ淶�ĵ�!");
				}
			}
			Element rootElt = xmlcontentdoc.getRootElement();
			Element em = rootElt.element("content")
					.element("EventCharacteristics").element("attributes");
			Iterator partxmlIterator = partxmldoc.getRootElement()
					.elementIterator("EventAttribute");
			while (partxmlIterator.hasNext()) {
				Element element = (Element) partxmlIterator.next();
				em.addText(element.asXML().replace("&lt;", "<")
						.replace("&gt;", ">"));
			}
			return rootElt.asXML().replace("&lt;", "<").replace("&gt;", ">");

		} catch (DocumentException e) {
			e.printStackTrace();
			return xmlcontent;
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws XMLnotFormatException {
		EventCommonXMLUtil ecxu = new EventCommonXMLUtil();
		System.out.println(ecxu.insertBasicPhase(FileOperationUtil
				.ReadFileContent("D:\\���׶��¼�ע��\\��1�׶�.xml", "UTF-8"),
				FileOperationUtil.ReadFileContent("D:\\���׶��¼�ע��\\��2�׶�.xml",
						"UTF-8"), "Preparation"));
	}

}
