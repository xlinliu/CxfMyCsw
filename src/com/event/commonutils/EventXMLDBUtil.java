package com.event.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.dom4j.DocumentException;
import org.hibernate.PropertyNotFoundException;
import org.w3c.dom.NodeList;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.service.commonutil.NsSolver;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.exceptions.XMLnotFormatException;
import com.csw.utils.GetRealPathUtil;

/**
 * �ṩ���ģ��Ԫ���ݴ洢���ܣ���������xml�ļ���Ԫ����
 * 
 * @author yxliang
 * 
 */
public class EventXMLDBUtil {
	private static volatile EventXMLDBUtil INSTANCE = null;
	// xml�洢λ��
	private static String XMLSTORE_LOCATION = "";
	// ��������ǰ׺
	private static Map<String, String> properties = new HashMap<String, String>();
	// �����������ĵ��б�������Ժ����ѯ·����ӳ���ϵ
	private static Map<String, String> propertyMap = new HashMap<String, String>();
	private static Map<String, String> prefix2uri = new HashMap<String, String>();
	private static Map<String, String> uri2prefix = new HashMap<String, String>();

	public static EventXMLDBUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EventXMLDBUtil();
		}
		return INSTANCE;
	}

	@Override
	protected void finalize() throws Throwable {
		properties = null;
		super.finalize();
	}

	private EventXMLDBUtil() {
		GetRealPathUtil grp = new GetRealPathUtil();
		String systemconfigpath = grp.getWebInfPath()
				+ "systemconfig.properties";
		try {

			XMLSTORE_LOCATION = FileOperationUtil.getPropertyValue(
					systemconfigpath, "EVENT_LOCATION");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			properties = FileOperationUtil.getAllPropertys(grp.getWebInfPath()
					+ "Event_namespace.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<String> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String prefix = iterator.next();
			String uri = properties.get(prefix);
			prefix2uri.put(prefix, uri);
			uri2prefix.put(uri, prefix);
		}
		try {
			String filepath = grp.getWebInfPath() + "Event_Metadata.properties";
			System.out.println("filepath: " + filepath);
			propertyMap = FileOperationUtil.getAllPropertys(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		INSTANCE = this;
	}

	/**
	 * ����ģ�͵�Berkeley���ݿ���
	 * 
	 * @return ����ɹ�����true��ʧ�ܷ���false
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentException
	 * @throws XmlException
	 * @throws XMLnotFormatException
	 * @throws EventBeginException
	 * @throws DocumentException
	 * @throws ServiceException
	 */
	public Boolean SaveEventXMLToBerkeley(String docname, String docmentcontent)
			throws DocumentnotExistException, XMLnotFormatException,
			XMLDocumentException, DocumentException, EventBeginException,
			ServiceException {
		return EventBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION, docname,
				docmentcontent);

	}

	/**
	 * �ж��Ƿ��ǺϷ���xml�ĵ�
	 * 
	 * @param doccontent
	 * @return
	 * @throws ServiceException
	 */
	public static void isformatdocument(String doccontent)
			throws ServiceException {
		try {
			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer transFormer = transFact.newTransformer();
			DOMResult dom = new DOMResult();
			transFormer.transform(
					new StreamSource(FileOperationUtil
							.getStringReader(doccontent)), dom);
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpath.setNamespaceContext(new NsSolver(prefix2uri, uri2prefix));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new ServiceException("���ݵ�xml�ĵ����󣬻����������� !");
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			throw new ServiceException("���ݵ�xml�ĵ����󣬻����������� !");
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new ServiceException("���ݵ�xml�ĵ����󣬻����������� !");
		}
	}

	/**
	 * ɾ��������Berkeley���ݿ��е��ĵ�
	 * 
	 * @param documentcontent
	 * @throws XMLDocumentNotExistException
	 * @throws DocumentnotExistException
	 */
	public void deleteEventXMLFromBerkeley(String docname)
			throws XMLDocumentNotExistException, DocumentnotExistException {
		EventBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION, docname);
	}

	/**
	 * ���±�����Berkeley���ݿ��е�ģ��
	 * 
	 * @param documentcontent
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 * @throws XMLDocumentException
	 * @throws XmlException
	 * @throws XMLnotFormatException
	 * @throws EventBeginException
	 * @throws DocumentException
	 * @throws ServiceException
	 */
	public void updateEventXMLToBerkeley(String docname, String documentcontent)
			throws DocumentnotExistException, XMLnotFormatException,
			XMLDocumentException, DocumentException, EventBeginException,
			XMLDocumentNotExistException, ServiceException {
		if (EventBerkeleyDBXMLUtil.isDocumentExist(XMLSTORE_LOCATION, docname)) {
			EventBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION, docname);
			EventBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION, docname,
					documentcontent);
			EventInfoWriter.deleteDocumentFromEventInfo(docname);
			EventInfoWriter.writeDocument2EventBasicInfo(docname);
		} else {
			throw new XMLDocumentNotExistException("ָ���ĵ�" + docname + "��������!");
		}
	}

	/**
	 * ��ѯ�������
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void queryEventXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * ʵ����ѯ
	 * 
	 * @param xm
	 * @throws XPathExpressionException
	 * @throws DocumentnotExistException
	 */
	@SuppressWarnings("unchecked")
	public static List query(String queryStr, String resultType, String docname)
			throws DirectoryNotExistException, FileNotFoundException,
			TransformerException, XPathExpressionException,
			DocumentnotExistException {
		List<String> resultsList = new ArrayList<String>();
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer transFormer = transFact.newTransformer();
		DOMResult dom;
		dom = new DOMResult();
		String docnameString = EventBerkeleyDBXMLUtil.getDocumentName(
				XMLSTORE_LOCATION, docname);
		transFormer.transform(new StreamSource(new FileInputStream(new File(
				XMLSTORE_LOCATION + File.separator + docnameString + ".xml"))),
				dom);
		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NsSolver(prefix2uri, uri2prefix));
		XPathExpression expression = xpath.compile(queryStr);
		NodeList nodeList = (NodeList) expression.evaluate(dom.getNode(),
				XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			resultsList.add(nodeList.item(i).getTextContent());
		}
		return resultsList;
	}

	/**
	 * ��ѯ�����ĵ�������
	 * 
	 * @param xm
	 *            xmlmanager
	 * @param propertyname
	 *            ��ѯ������
	 * @param docname
	 *            �ĵ�������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryDocumentOfEvent(String propertyname, String docname) {
		String queryString = "";
		try {
			if (propertyMap.size() == 0) {
				System.out.println("propertyMapΪ��!");
			}
			String queryStr = propertyMap.get(propertyname);
			if (queryStr == null) {
				throw new PropertyNotFoundException("����" + propertyname
						+ "δ������!");
			}
			String ttpath = queryStr.split(" ")[0];
			String basequerypath = ttpath;
			String basenodetype = queryStr.split(" ")[1];
			if (propertyname.startsWith("second")
					|| propertyname.startsWith("third")
					|| propertyname.startsWith("four")) {
				basequerypath = ttpath
						.replace("eml:Event/",
								"eml:Event/eml:content/eml:EventCharacteristics/eml:attributes/");
			}
			if (basenodetype.equals("text")) {
				queryString = "//" + basequerypath + "";
			} else if (basenodetype.equals("number")) {
				queryString = "//" + basequerypath + "";
			}
			// System.out.println("......" + queryString);
			List list = query(queryString, basenodetype, docname);
			// System.out.println("    " + list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllDocumentName() throws DirectoryNotExistException {
		List<String> results = new ArrayList<String>();
		for (String str : EventBerkeleyDBXMLUtil
				.getAllDocumentName(XMLSTORE_LOCATION)) {
			results.add(str);
		}
		return results;
	}

	public String getDocument(String docname) throws DocumentnotExistException {
		return EventBerkeleyDBXMLUtil.readDocument(XMLSTORE_LOCATION, docname);
	}

	public static String getXMLSTORE_LOCATION() {
		return XMLSTORE_LOCATION;
	}
}
