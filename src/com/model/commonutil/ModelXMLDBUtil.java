package com.model.commonutil;

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
import org.hibernate.PropertyNotFoundException;
import org.w3c.dom.NodeList;

import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.ServiceException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.service.commonutil.NsSolver;
import com.csw.utils.GetRealPathUtil;

/**
 * �ṩ���ģ��Ԫ���ݴ洢���ܣ���������xml�ļ���Ԫ����
 * 
 * @author yxliang
 * 
 */
public class ModelXMLDBUtil {
	private static volatile ModelXMLDBUtil INSTANCE = null;
	// xml�洢λ��
	private static String XMLSTORE_LOCATION = "";
	// ��������ǰ׺
	private static Map<String, String> properties = new HashMap<String, String>();
	// �����������ĵ��б�������Ժ����ѯ·����ӳ���ϵ
	private static Map<String, String> propertyMap = new HashMap<String, String>();
	private static Map<String, String> prefix2uri = new HashMap<String, String>();
	private static Map<String, String> uri2prefix = new HashMap<String, String>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Long pre = System.currentTimeMillis();
		List<String> strings = new ModelXMLDBUtil().queryDocumentOfModel(
				"model_identification_keywords_keyword",
				"ShortestPathAnalysisModel");
		Long nowLong = System.currentTimeMillis();
		System.out.println("cost time: " + (nowLong - pre) + " ms");
		for (String st : strings) {
			System.out.println(st);
		}
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
			transFormer.transform(new StreamSource(
					FileOperationUtil
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

	public static ModelXMLDBUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModelXMLDBUtil();
		}
		return INSTANCE;
	}

	@Override
	protected void finalize() throws Throwable {
		properties = null;
		super.finalize();
	}

	private ModelXMLDBUtil() {
		GetRealPathUtil grp = new GetRealPathUtil();
		String systemconfigpath = grp.getWebInfPath()
				+ "systemconfig.properties";
		try {
			XMLSTORE_LOCATION = FileOperationUtil.getPropertyValue(
					systemconfigpath, "MODEL_XML_LOCATION");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			properties = FileOperationUtil.getAllPropertys(grp.getWebInfPath()
					+ "Model_NAMESPACES.properties");
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
			String filepath = grp.getWebInfPath() + "Model_Metedata.properties";
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
	 * @throws ServiceException
	 * @throws XmlException
	 */
	public Boolean SaveModelXMLToBerkeley(String docname, String docmentcontent)
			throws XMLDocumentException, ServiceException {
		return ModelBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION, docname,
				docmentcontent);

	}

	/**
	 * ɾ��������Berkeley���ݿ��е��ĵ�
	 * 
	 * @param documentcontent
	 * @throws XMLDocumentNotExistException
	 * @throws DocumentnotExistException
	 */
	public void deleteModelXMLFromBerkeley(String docname)
			throws DocumentnotExistException {
		ModelBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION, docname);
	}

	/**
	 * ���±�����Berkeley���ݿ��е�ģ��
	 * 
	 * @param documentcontent
	 * @throws XMLDocumentNotExistException
	 * @throws XMLDocumentException
	 * @throws XmlException
	 * @throws DocumentnotExistException
	 * @throws ServiceException
	 */
	public void updateModelXMLToBerkeley(String docname, String documentcontent)
			throws XMLDocumentNotExistException, XMLDocumentException,
			DocumentnotExistException, ServiceException {
		if (ModelBerkeleyDBXMLUtil.isDocumentExist(XMLSTORE_LOCATION, docname)) {
			ModelBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION, docname);
			ModelBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION, docname,
					documentcontent);
			ModelInfoWriter.deleteDocumentFromModelBasicInfo(docname);
			ModelInfoWriter.writeDocument2ModelBasicInfo(docname);
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
	public void queryModelXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * ʵ����ѯ
	 * 
	 * @param xm
	 * @throws XPathExpressionException
	 */
	@SuppressWarnings("unchecked")
	public static List query(String queryStr, String resultType, String docname)
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
		String filepath = grp.getWebInfPath() + "Model_Metedata.properties";
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
	public List queryDocumentOfModel(String propertyname, String docname) {
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
				queryString = "//" + basequerypath + "";
			} else if (basenodetype.equals("number")) {
				queryString = "//" + basequerypath + "";
			}
			List list = query(queryString, basenodetype, docname);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllDocumentName() throws DirectoryNotExistException {
		List<String> results = new ArrayList<String>();
		for (String str : ModelBerkeleyDBXMLUtil
				.getAllDocumentName(XMLSTORE_LOCATION)) {
			results.add(str);
		}
		return results;
	}

	public String getDocument(String docname) throws DocumentnotExistException {
		return ModelBerkeleyDBXMLUtil.readDocument(XMLSTORE_LOCATION, docname);
	}

	public static String getXMLSTORE_LOCATION() {
		return XMLSTORE_LOCATION;
	}
}
