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
 * �ṩ���ģ��Ԫ���ݴ洢���ܣ���������xml�ļ���Ԫ����
 * 
 * @author yxliang
 * 
 */
public class ServiceChainXMLDBUtil {
	private static volatile ServiceChainXMLDBUtil INSTANCE = null;
	// xml�洢λ��
	private static String XMLSTORE_LOCATION = "";
	// ��������ǰ׺
	private static Map<String, String> properties = new HashMap<String, String>();
	private static Map<String, String> prefix2uri = new HashMap<String, String>();
	private static Map<String, String> uri2prefix = new HashMap<String, String>();
	// �����������ĵ��б�������Ժ����ѯ·����ӳ���ϵ
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
	 * ����ģ�͵�Berkeley���ݿ���
	 * 
	 * @return ����ɹ�����true��ʧ�ܷ���false
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
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 */
	public void deleteServiceChainXMLFromBerkeley(String docname)
			throws DocumentnotExistException {
		ServiceChainBerkeleyDBXMLUtil
				.deleteDocument(XMLSTORE_LOCATION, docname);
	}

	/**
	 * ���±�����Berkeley���ݿ��е�ģ��
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
			throw new XMLDocumentNotExistException("ָ���ĵ�" + docname + "��������!");
		}
	}

	/**
	 * ��ѯ�������
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	public void queryServiceChainXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * ��ѯ�������е�figures��Ϣ
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
	 * ��ѯ�������еĸ��Ӵ��������Ϣ
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
	 * ��ѯ�������еĲ�����Ϣ
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
	 * ʵ����ѯ
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
	 * ��ȡָ�����ԵĲ�ѯ·��
	 * 
	 * @param propertyname
	 *            ��Ҫ��ѯ����
	 * @return ���ظ�����xml�ĵ��е�·����Ϣ
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
					"��Model_Metedata.properties�����ļ��в������ڸ�������!");
		}
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
	public List queryDocumentOfServiceChain(String propertyname, String docname) {
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
