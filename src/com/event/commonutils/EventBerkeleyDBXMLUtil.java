package com.event.commonutils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.EventBeginException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLnotFormatException;
import com.event.customTypes.EventBasicInfo;

/**
 * berkeley db xml数据库建设
 * 
 * @author yxliang
 * 
 */
public class EventBerkeleyDBXMLUtil {
	public static String EVENT_PHASE_URL = "http://wwww.document#";// 保存在文档元数据中的url地址
	public static String EVENT_PHASE_NAME = "EVENT_PHASE";// 保存在文档元数据中的事件阶段名称

	/**
	 * 获取指定存储系统中的全部文件名称
	 * 
	 * @param dirpath
	 * @return
	 * @throws DirectoryNotExistException
	 */
	public static String[] getAllDocumentName(String dirpath)
			throws DirectoryNotExistException {
		File file = new File(dirpath);
		if (file.exists() && file.isDirectory()) {
			String[] tt = file.list();
			Integer len = tt.length;
			String[] result = new String[len];

			for (int i = 0; i < tt.length; i++) {
				String str = tt[i];
				if (str.endsWith("_001.xml") || str.endsWith("_002.xml")
						|| str.endsWith("_003.xml")) {
					result[i] = str.substring(0, str.length() - 8);
				} else {
					result[i] = str.substring(0, str.length() - 4);
				}
			}
			return result;
		} else {
			throw new DirectoryNotExistException("事件存储系统路径不存在!");
		}
	}

	/**
	 * 判断文档是否存在
	 * 
	 * @param xc
	 * @param docname
	 * @return
	 */
	public static Boolean isDocumentExist(String dirpath, String docname) {
		File dir = new File(dirpath);
		if (dir.exists() && dir.isDirectory()) {
			for (String str : dir.list()) {
				if (str.contains(docname)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断文档是否存在
	 * 
	 * @param xc
	 * @param docname
	 * @return
	 * @throws DocumentnotExistException
	 */
	public static String getDocumentName(String dirpath, String docname)
			throws DocumentnotExistException {
		File dir = new File(dirpath);

		if (dir.exists() && dir.isDirectory()) {
			String result = "";
			for (String str : dir.list()) {
				if (str.contains(docname)) {
					result = str;
					result = result.substring(0, result.length() - 4);
					break;

				}
			}
			return result;
		} else {
			throw new DocumentnotExistException("文档不存在!");
		}
	}

	/**
	 * 读取xc中全部的文档的内容
	 * 
	 * @param xc
	 * @param docnames
	 * @return 
	 *         返回所有的文档名和对应文档内容的信息，其中，如果制定的文档名没有相应的文档，其key-value中value的内容为"no document"
	 */
	public static Map<String, String> readDocuments(String dirpath,
			List<String> docnames) {
		Map<String, String> maps = new HashMap<String, String>();
		for (String docname : docnames) {
			try {
				maps.put(docname, readDocument(dirpath, docname));
			} catch (Exception e) {
				maps.put(docname, "no document");
			}
		}
		return maps;
	}

	public static String readDocument(String dirpath, String docname)
			throws DocumentnotExistException {
		if (!isDocumentExist(dirpath, docname)) {
			throw new DocumentnotExistException("事件存储系统中文档【" + docname
					+ "】不存在!");
		}
		try {
			String recontent = FileOperationUtil.ReadFileContent(dirpath
					+ File.separator + getDocumentName(dirpath, docname)
					+ ".xml", "UTF-8");
			return recontent;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 删除指定容器内制定的全部文档的信息，
	 * 
	 * @param xc
	 * @param docnames
	 * @return 返回所有文档的删除情况，如果删除成功key-value返回success，否则返回no document
	 */
	public static Map<String, String> deleteDocument(String dirpath,
			List<String> docnames) {
		Map<String, String> maps = new HashMap<String, String>();
		for (String docname : docnames) {
			try {
				deleteDocument(dirpath, docname);
				maps.put(docname, "success");
			} catch (DocumentnotExistException e) {
				maps.put(docname, "no document");
			}
		}
		return maps;

	}

	/**
	 * 删除制定容器内的指定文档的信息
	 * 
	 * @param xc
	 * @param docname
	 * @throws DocumentnotExistException
	 *             文档不存在
	 */
	public static void deleteDocument(String dirpath, String docname)
			throws DocumentnotExistException {
		if (isDocumentExist(dirpath, docname)) {
			File file = new File(dirpath + File.separator
					+ getDocumentName(dirpath, docname) + ".xml");
			file.delete();
			EventInfoWriter.deleteDocumentFromEventInfo(docname);
		} else {
			throw new DocumentnotExistException("事件存储系统中无文档【" + docname
					+ "】存在!");
		}

	}

	/**
	 * 增加文档至制定的xmlcontainer容器中
	 * 
	 * @param xc
	 *            执行的xmlcontainer容器中
	 * @param docname
	 *            文档的名称
	 * @param doccontent
	 *            文档的内容
	 * @throws XmlException
	 * @throws XMLDocumentException
	 * @throws XMLnotFormatException
	 * @throws DocumentException
	 * @throws EventBeginException
	 * @throws DocumentnotExistException
	 * @throws ServiceException
	 */
	public static Boolean putDocument(String dirpath, String docname,
			String firstdoccontent) throws DocumentnotExistException,
			XMLnotFormatException, XMLDocumentException, DocumentException,
			EventBeginException, ServiceException {
		if (firstdoccontent == null || firstdoccontent.equals("")
				|| firstdoccontent.trim().equals("")) {
			throw new DocumentException("传递的xml文档内容不能为空 !");
		}
		String doccontent = "";
		try {
			doccontent = new String(firstdoccontent.getBytes(), "GBK").replace(
					"?<?", "<?");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// System.out.println("doccontent: " + doccontent);
		EventXMLDBUtil.isformatdocument(doccontent);
		if (isDocumentExist(dirpath, docname)) {
			String docnameStr = getDocumentName(dirpath, docname);
			String xmlcontent = FileOperationUtil.ReadFileContent(dirpath
					+ File.separator + docnameStr + ".xml", "UTF-8");
			String xmltempcontent = "";
			Integer houzhui = 1;
			if (getDocumentName(dirpath, docname).endsWith("_001")) {
				xmlcontent = xmlcontent.replace(
						"<eml:eventPeriod>Monitoring</eml:eventPeriod>",
						"<eml:eventPeriod>Preparation</eml:eventPeriod>");
				// 保存第二阶段
				xmltempcontent = EventCommonXMLUtil.insertPreparationPhase(
						xmlcontent, doccontent);
				houzhui = 2;
			} else if (getDocumentName(dirpath, docname).endsWith("_002")) {
				// 保存第三阶段
				xmlcontent = xmlcontent.replace(
						"<eml:eventPeriod>Preparation</eml:eventPeriod>",
						"<eml:eventPeriod>Response</eml:eventPeriod>");
				xmltempcontent = EventCommonXMLUtil.insertResponsePhase(
						xmlcontent, doccontent);
				houzhui = 3;
			} else if (getDocumentName(dirpath, docname).endsWith("_003")) {
				houzhui = 4;
				// 保存第四阶段
				xmlcontent = xmlcontent.replace(
						"<eml:eventPeriod>Response</eml:eventPeriod>",
						"<eml:eventPeriod>Recovery</eml:eventPeriod>");
				xmltempcontent = EventCommonXMLUtil.insertRecoveryPhase(
						xmlcontent, doccontent);

			} else {
				throw new XMLDocumentException("名称为" + docname + "的xml文档已经存在!");
			}
			if (houzhui == 2) {
				FileOperationUtil.DeleteFile(dirpath + File.separator + docname
						+ "_001" + ".xml");
				FileOperationUtil.WriteToFileContent(xmltempcontent, dirpath
						+ File.separator + docname + "_002" + ".xml", "UTF-8");
			} else if (houzhui == 3) {
				FileOperationUtil.DeleteFile(dirpath + File.separator + docname
						+ "_002" + ".xml");
				FileOperationUtil.WriteToFileContent(xmltempcontent, dirpath
						+ File.separator + docname + "_003" + ".xml", "UTF-8");
			} else if (houzhui == 4) {
				FileOperationUtil.DeleteFile(dirpath + File.separator + docname
						+ "_003" + ".xml");
				FileOperationUtil.WriteToFileContent(xmltempcontent, dirpath
						+ File.separator + docname + ".xml", "UTF-8");
			}
			// 删除其中的要求
			for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
				if (ebi.getDocname().equals(docname)) {
					EventInfoConfig.getEbis().remove(ebi);
					break;
				}
			}
		} else {
			Document xmlcontentdoc = DocumentHelper.parseText(doccontent); // 将字符串转为XML
			Element testelement = xmlcontentdoc.getRootElement().element(
					"eventPeriod");
			String houzhui = "_001";
			if (testelement != null) {
				throw new EventBeginException("必须首先注册事件第一阶段XML文档!");
			}
			FileOperationUtil.WriteToFileContent(doccontent, dirpath
					+ File.separator + docname + houzhui + ".xml", "UTF-8");

		}
		EventInfoWriter.writeDocument2EventBasicInfo(docname);
		return true;

	}
}
