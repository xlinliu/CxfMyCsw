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
 * 提供针对模型元数据存储功能，包括增加xml文件的元数据
 * 
 * @author yxliang
 * 
 */
public class EventXMLDBUtil {
	private static volatile EventXMLDBUtil INSTANCE = null;
	// xml存储位置
	private static String XMLSTORE_LOCATION = "";
	// 各种命名前缀
	private static Map<String, String> properties = new HashMap<String, String>();
	// 保存在属性文档中保存的属性和其查询路径的映射关系
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
	 * 保存模型到Berkeley数据库中
	 * 
	 * @return 保存成功返回true，失败返回false
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
	 * 判断是否是合法的xml文档
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
			throw new ServiceException("传递的xml文档错误，或编码错误，请检测 !");
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			throw new ServiceException("传递的xml文档错误，或编码错误，请检测 !");
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new ServiceException("传递的xml文档错误，或编码错误，请检测 !");
		}
	}

	/**
	 * 删除保存在Berkeley数据库中的文档
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
	 * 更新保存在Berkeley数据库中的模型
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
			throw new XMLDocumentNotExistException("指定文档" + docname + "并不存在!");
		}
	}

	/**
	 * 查询相关资料
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void queryEventXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * 实例查询
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
	 * 查询单个文档的内容
	 * 
	 * @param xm
	 *            xmlmanager
	 * @param propertyname
	 *            查询的属性
	 * @param docname
	 *            文档的名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryDocumentOfEvent(String propertyname, String docname) {
		String queryString = "";
		try {
			if (propertyMap.size() == 0) {
				System.out.println("propertyMap为空!");
			}
			String queryStr = propertyMap.get(propertyname);
			if (queryStr == null) {
				throw new PropertyNotFoundException("属性" + propertyname
						+ "未被发现!");
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
