package com.datametedia.commonutil;

import java.io.File;
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
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.service.commonutil.NsSolver;
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
public class MetedataXMLDBUtil {
	private static volatile MetedataXMLDBUtil INSTANCE = null;
	// xml�洢λ��
	private static String XMLSTORE_LOCATION = "";
	// ��������ǰ׺
	private static Map<String, String> properties = new HashMap<String, String>();
	// �����������ĵ��б�������Ժ����ѯ·����ӳ���ϵ
	private static Map<String, String> propertyMap = new HashMap<String, String>();
	private static Map<String, String> prefix2uri = new HashMap<String, String>();
	private static Map<String, String> uri2prefix = new HashMap<String, String>();

	public static MetedataXMLDBUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MetedataXMLDBUtil();
		}
		return INSTANCE;
	}

	@Override
	protected void finalize() throws Throwable {
		properties = null;
		super.finalize();
	}

	private MetedataXMLDBUtil() {
		GetRealPathUtil grp = new GetRealPathUtil();
		String systemconfigpath = grp.getWebInfPath()
				+ "systemconfig.properties";
		try {

			XMLSTORE_LOCATION = FileOperationUtil.getPropertyValue(
					systemconfigpath, "DATA_METEDATA_XML_LOCATION");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			properties = FileOperationUtil.getAllPropertys(grp.getWebInfPath()
					+ "METADATA_NAMESPACES.properties");
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
			String filepath = grp.getWebInfPath() + "Data_Metadata.properties";
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
	public Boolean SaveMetadataXMLToBerkeley(String docname,
			String docmentcontent) throws XMLDocumentException,
			ServiceException {
		return MetadataBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION,
				docname, docmentcontent);

	}

	/**
	 * ɾ��������Berkeley���ݿ��е��ĵ�
	 * 
	 * @param documentcontent
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 */
	public void deleteMetadataXMLFromBerkeley(String docname)
			throws XMLDocumentNotExistException, DocumentnotExistException {
		MetadataBerkeleyDBXMLUtil.deleteDocument(XMLSTORE_LOCATION, docname);
	}

	/**
	 * ���±�����Berkeley���ݿ��е�ģ��
	 * 
	 * @param documentcontent
	 * @throws DocumentnotExistException
	 * @throws XMLDocumentNotExistException
	 * @throws XMLDocumentException
	 * @throws ServiceException
	 * @throws XmlException
	 */
	public void updateMetadataXMLToBerkeley(String docname,
			String documentcontent) throws XMLDocumentNotExistException,
			DocumentnotExistException, XMLDocumentException, ServiceException {
		if (MetadataBerkeleyDBXMLUtil.isDocumentExist(XMLSTORE_LOCATION,
				docname)) {
			MetadataBerkeleyDBXMLUtil
					.deleteDocument(XMLSTORE_LOCATION, docname);
			MetadataBerkeleyDBXMLUtil.putDocument(XMLSTORE_LOCATION, docname,
					documentcontent);
			MetadataInfoWriter.deleteForMetadataConfig(docname);
			MetadataInfoWriter.write2MetadataBasicInfo(docname);
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
	public void queryMetadataXMLFromBerkeley(String propertyName,
			Object propertyValue) {
	}

	/**
	 * ʵ����ѯ
	 * 
	 * @param xm
	 * @throws DirectoryNotExistException
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 * @throws XPathExpressionException
	 * @throws DatabaseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List queryWithContent(String queryStr, String resultType,
			String filecontent) throws DirectoryNotExistException,
			FileNotFoundException, TransformerException,
			XPathExpressionException {
		List<String> resultsList = new ArrayList<String>();
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer transFormer = transFact.newTransformer();
		DOMResult dom;
		dom = new DOMResult();
		String strcontent = filecontent;
		String fileString = "<geodataCoreMeta xsi:schemaLocation=\"http://www.geodata.cn/metadata/GeodataCore http://www.geodata.cn/Portal/metadata/docs/schemas/GeoCore2.xsd\" xmlns=\"http://www.geodata.cn/metadata/GeodataCore\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
		String temp = "<geodataCoreMeta>";
		if (strcontent.startsWith("?")) {
			strcontent = strcontent.substring(1);
		}
		strcontent = strcontent.replace(fileString, temp);
		transFormer.transform(
				new StreamSource(FileOperationUtil
						.getStringReader(strcontent)), dom);
		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NsSolver(prefix2uri, uri2prefix));
		XPathExpression expression = xpath.compile(queryStr);
		NodeList nodeList = (NodeList) expression.evaluate(dom.getNode(),
				XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
//			resultsList.add(nodeList.item(i).getTextContent());
			//resultsList.add(nodeList.item(i).getNodeValue());
		}
		return resultsList;
	}

	/**
	 * ��ȡxml�ĵ���Ϣ
	 * 
	 * @param docname
	 * @return
	 */
	public static String getXMLContent(String docname) {
		String strcontent = FileOperationUtil.ReadFileContent(
				XMLSTORE_LOCATION + File.separator + docname + ".xml", "UTF-8")
				.trim();
		return strcontent;
	}

	/**
	 * ʵ����ѯ
	 * 
	 * @param xm
	 * @throws DirectoryNotExistException
	 * @throws TransformerException
	 * @throws FileNotFoundException
	 * @throws XPathExpressionException
	 * @throws DatabaseException
	 * @throws IOException
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
		String strcontent = FileOperationUtil.ReadFileContent(
				XMLSTORE_LOCATION + File.separator + docname + ".xml", "UTF-8")
				.trim();

		String fileString = "<geodataCoreMeta xsi:schemaLocation=\"http://www.geodata.cn/metadata/GeodataCore http://www.geodata.cn/Portal/metadata/docs/schemas/GeoCore2.xsd\" xmlns=\"http://www.geodata.cn/metadata/GeodataCore\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
		String temp = "<geodataCoreMeta>";
		if (strcontent.startsWith("?")) {
			strcontent = strcontent.substring(1);
		}
		strcontent = strcontent.replace(fileString, temp);
		transFormer.transform(
				new StreamSource(FileOperationUtil
						.getStringReader(strcontent)), dom);
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
	 * ��ȡָ�����ԵĲ�ѯ·��
	 * 
	 * @param propertyname
	 *            ��Ҫ��ѯ����
	 * @return ���ظ�����xml�ĵ��е�·����Ϣ
	 */
	public static String getQueryPath(String propertyname)
			throws PropertyNotFoundException {
		GetRealPathUtil grp = new GetRealPathUtil();
		String filepath = grp.getWebInfPath() + "Data_Metadata.properties";
		try {
			String queryStr = FileOperationUtil.getPropertyValue(filepath,
					propertyname);
			String basequerypath = queryStr.split(" ")[0];
			return basequerypath;
		} catch (Exception e) {
			throw new PropertyNotFoundException(
					"��Data_Metedata.properties�����ļ��в������ڸ�������!");
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
	public List queryDocumentOfMetadata(String propertyname, String docname) {
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
		for (String str : MetadataBerkeleyDBXMLUtil
				.getAllDocumentName(XMLSTORE_LOCATION)) {
			results.add(str);
		}
		return results;
	}

	public String getDocument(String docname) throws DocumentnotExistException {
		return MetadataBerkeleyDBXMLUtil.readDocument(XMLSTORE_LOCATION,
				docname);
	}

	public static String getXMLSTORE_LOCATION() {
		return XMLSTORE_LOCATION;
	}

}
