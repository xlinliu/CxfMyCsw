package com.event.commonutils;

import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.XMLnotFormatException;

/**
 * 将事件描述文档进行处理
 * 
 * @author yxliang
 * 
 */
public class EventCommonXMLUtil {

	/**
	 * 将preparationxml部分插入到xmlcontent中
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
	 * 将responsexml部分插入到xmlcontent中
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
	 * 将responsexml部分插入到xmlcontent中
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
	 * 将event的部分信息插入到完整的xml文档中
	 * 
	 * @param xmlcontent
	 * @param partxml
	 * @param pahseStr
	 *            需要增加的阶段
	 * @return 返回整合后（增加EventAttribute） 的文档的内容，如果出现异常，则返回xmlcontent
	 * @throws XMLnotFormatException
	 */
	@SuppressWarnings("unchecked")
	private static String insertBasicPhase(String xmlcontent, String partxml,
			String pahseStr) throws XMLnotFormatException {
		Document xmlcontentdoc = null;
		Document partxmldoc = null;
		try {
			xmlcontentdoc = DocumentHelper.parseText(xmlcontent); // 将字符串转为XML
			partxmldoc = DocumentHelper.parseText(partxml);
			Element testelement = partxmldoc.getRootElement().element(
					"eventPeriod");
			if (testelement == null) {
				throw new XMLnotFormatException("传递的xml文档不规范!");
			} else {
				if (!testelement.getText().equals(pahseStr)) {
					throw new XMLnotFormatException("传递的xml文档不是事件[" + pahseStr
							+ "]阶段的规范文档!");
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
				.ReadFileContent("D:\\各阶段事件注册\\第1阶段.xml", "UTF-8"),
				FileOperationUtil.ReadFileContent("D:\\各阶段事件注册\\第2阶段.xml",
						"UTF-8"), "Preparation"));
	}

}
