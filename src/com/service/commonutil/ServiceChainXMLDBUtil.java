package com.service.commonutil;

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
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.PropertyNotFoundException;
import org.w3c.dom.NodeList;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.GetRealPathUtil;

/**
 * 提供针对模型元数据存储功能，包括增加xml文件的元数据
 * 
 * @author yxliang
 * 
 */
public class ServiceChainXMLDBUtil {
	private static volatile ServiceChainXMLDBUtil INSTANCE = null;
	// xml存储位置
	private static String XMLSTORE_LOCATION = "";
	// 各种命名前缀
	private static Map<String, String> properties = new HashMap<String, String>();
	private static Map<String, String> prefix2uri = new HashMap<String, String>();
	private static Map<String, String> uri2prefix = new HashMap<String, String>();
	// 保存在属性文档中保存的属性和其查询路径的映射关系
	private static Map<String, String> propertyMap = new HashMap<String, String>();

	public static ServiceChainXMLDBUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ServiceChainXMLDBUtil();
		}
		return INSTANCE;
	}

	@Override
	protected void finalize() throws Throwable {
		properties = null;
		super.finalize();
	}

	private ServiceChainXMLDBUtil() {
		GetRealPathUtil grp = new GetRealPathUtil();
		String systemconfigpath = grp.getWebInfPath()
				+ "systemconfig.properties";
		try {

			XMLSTORE_LOCATION = FileOperationUtil.getPropertyValue(
					systemconfigpath, "SERVICE_XML_LOCATION");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			properties = FileOperationUtil.getAllPropertys(grp.getWebInfPath()
					+ "servicechain_namespace.properties");
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
			String filepath = grp.getWebInfPath()
					+ "servicechain_metadata.properties";
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
	 * @throws XMLDocumentException
	 * @throws XmlException
	 * @throws DocumentException
	 * @throws ServiceException
	 */
	public Boolean SaveServiceChainXMLToBerkeley(String docname,
			String docmentcontent) throws XMLDocumentException,
			DocumentException, ServiceException {
		return ServiceChainBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION,
				docname, docmentcontent);

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
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 */
	public void deleteServiceChainXMLFromBerkeley(String docname)
			throws DocumentnotExistException {
		ServiceChainBerkeleyDBXMLUtil
				.deleteDocument(XMLSTORE_LOCATION, docname);
	}

	/**
	 * 更新保存在Berkeley数据库中的模型
	 * 
	 * @param documentcontent
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 * @throws XMLDocumentException
	 * @throws XmlException
	 * @throws DocumentException
	 * @throws ServiceException
	 */
	public void updateServiceChainXMLToBerkeley(String docname,
			String documentcontent) throws DocumentnotExistException,
			DocumentException, XMLDocumentException,
			XMLDocumentNotExistException, ServiceException {
		if (ServiceChainBerkeleyDBXMLUtil.isDocumentExist(XMLSTORE_LOCATION,
				docname)) {
			//ServiceChainlInfoWriter.deleteDocumentFromServiceChainInfo(docname);
			ServiceChainBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION,
					docname);
			ServiceChainBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION,
					docname, documentcontent);
			//ServiceChainlInfoWriter.writeDocument2ServiceChainInfo(docname);
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
	public void queryServiceChainXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * 查询服务链中的figures信息
	 * 
	 * @param queryStr
	 * @param basenodetype
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List queryservice_Figures(String queryStr, String basenodetype,
			String docname) throws DocumentException {
		List<String> resultsList = new ArrayList<String>();
		Document document = DocumentHelper.parseText(FileOperationUtil
				.ReadFileContent(XMLSTORE_LOCATION + File.separator + docname
						+ ".xml", "UTF-8"));
		resultsList.add(document.getRootElement().element("ComponentServices")
				.element("Figures").asXML());
		return resultsList;
	}

	/**
	 * 查询服务链中的复杂处理过程信息
	 * 
	 * @param queryStr
	 * @param basenodetype
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	private List queryservice_ComplexProcess(String queryStr,
			String basenodetype, String docname) throws DocumentException {
		List<String> resultsList = new ArrayList<String>();
		Document document = DocumentHelper.parseText(FileOperationUtil
				.ReadFileContent(XMLSTORE_LOCATION + File.separator + docname
						+ ".xml", "UTF-8"));
		resultsList.add(document.getRootElement().element("ComponentServices")
				.element("ComplexProcess").asXML());
		return resultsList;
	}

	/**
	 * 查询服务链中的参数信息
	 * 
	 * @param queryString
	 * @param resultType
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	private List queryservice_parameters(String queryStr, String resultType,
			String docname) throws DirectoryNotExistException,
			FileNotFoundException, TransformerException,
			XPathExpressionException, DocumentException {
		List<String> resultsList = new ArrayList<String>();
		Document document = DocumentHelper.parseText(FileOperationUtil
				.ReadFileContent(XMLSTORE_LOCATION + File.separator + docname
						+ ".xml", "UTF-8"));
		Element RootElement = document.getRootElement();
		Document xdocument = DocumentHelper.parseText(RootElement
				.element("ServiceParameters").element("Parameters").asXML());
		List<Element> list = xdocument.getRootElement().elements(
				"ParameterList");
		if (list != null && list.size() != 0) {
			for (Object em : list.get(0).elements()) {
				resultsList.add(((Element) em).asXML());
			}
		}
		return resultsList;

	}

	/**
	 * 实例查询
	 * 
	 * @param xm
	 * @throws DatabaseException
	 * @throws IOException
	 * @throws DirectoryNotExistException
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 * @throws XPathExpressionException
	 */
	@SuppressWarnings("unchecked")
	public List query(String queryStr, String resultType, String docname)
			throws DirectoryNotExistException, FileNotFoundException,
			TransformerException, XPathExpressionException {
		List<String> resultsList = new ArrayList<String>();
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer transFormer = transFact.newTransformer();
		DOMResult dom;
		dom = new DOMResult();
		transFormer.transform(new StreamSource(new FileInputStream(new File(
				XMLSTORE_LOCATION + File.separator + docname + ".xml"))), dom);
		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NsSolver(prefix2uri, uri2prefix));
		XPathExpression expression = xpath.compile(queryStr);
		// System.out.println(queryStr);
		NodeList nodeList = (NodeList) expression.evaluate(dom.getNode(),
				XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			resultsList.add(nodeList.item(i).getTextContent());
		}
		return resultsList;
	}

	/**
	 * 获取指定属性的查询路径
	 * 
	 * @param propertyname
	 *            需要查询的项
	 * @return 返回该项在xml文档中的路径信息
	 */
	public static String getQueryPath(String propertyname)
			throws PropertyNotFoundException {
		GetRealPathUtil grp = new GetRealPathUtil();
		String filepath = grp.getWebInfPath()
				+ "servicechain_metadata.properties";
		try {
			String queryStr = FileOperationUtil.getPropertyValue(filepath,
					propertyname);
			String basequerypath = queryStr.split(" ")[0];
			return basequerypath;
		} catch (Exception e) {
			throw new PropertyNotFoundException(
					"在Model_Metedata.properties属性文件中并不存在该属性项!");
		}
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
	public List queryDocumentOfServiceChain(String propertyname, String docname) {
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
			String basequerypath = queryStr.split(" ")[0];
			String basenodetype = queryStr.split(" ")[1];
			if (basenodetype.equals("text")) {
				queryString = "//" + basequerypath;
			} else if (basenodetype.equals("number")) {
				queryString = "//" + basequerypath;
			} else if (basenodetype.equals("element")) {
				queryString = "//" + basequerypath;
			}
			List list = new ArrayList();
			if (propertyname.equals("service_parameters")) {
				list = queryservice_parameters(queryString, basenodetype,
						docname);
			} else if (propertyname
					.equals("serviceChain_ComponentService_ComponentServices_ComplexProcess")) {
				list = queryservice_ComplexProcess(queryStr, basenodetype,
						docname);
			} else if (propertyname
					.equals("serviceChain_ComponentService_ComponentServices_Figures")) {
				list = queryservice_Figures(queryStr, basenodetype, docname);
			} else {
				System.out.println(queryString);
				list = query(queryString, basenodetype, docname);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllDocumentName() throws DirectoryNotExistException {
		List<String> results = new ArrayList<String>();
		for (String str : ServiceChainBerkeleyDBXMLUtil
				.getAllDocumentName(XMLSTORE_LOCATION)) {
			results.add(str);
		}
		return results;
	}

	public String getDocument(String docname) throws DocumentnotExistException {
		return ServiceChainBerkeleyDBXMLUtil.readDocument(XMLSTORE_LOCATION,
				docname);
	}

	public static String getXMLSTORE_LOCATION() {
		return XMLSTORE_LOCATION;
	}
}
