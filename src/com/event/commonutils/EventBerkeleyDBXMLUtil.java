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
 * berkeley db xml���ݿ⽨��
 * 
 * @author yxliang
 * 
 */
public class EventBerkeleyDBXMLUtil {
	public static String EVENT_PHASE_URL = "http://wwww.document#";// �������ĵ�Ԫ�����е�url��ַ
	public static String EVENT_PHASE_NAME = "EVENT_PHASE";// �������ĵ�Ԫ�����е��¼��׶�����

	/**
	 * ��ȡָ���洢ϵͳ�е�ȫ���ļ�����
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
			throw new DirectoryNotExistException("�¼��洢ϵͳ·��������!");
		}
	}

	/**
	 * �ж��ĵ��Ƿ����
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
	 * �ж��ĵ��Ƿ����
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
			throw new DocumentnotExistException("�ĵ�������!");
		}
	}

	/**
	 * ��ȡxc��ȫ�����ĵ�������
	 * 
	 * @param xc
	 * @param docnames
	 * @return 
	 *         �������е��ĵ����Ͷ�Ӧ�ĵ����ݵ���Ϣ�����У�����ƶ����ĵ���û����Ӧ���ĵ�����key-value��value������Ϊ"no document"
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
			throw new DocumentnotExistException("�¼��洢ϵͳ���ĵ���" + docname
					+ "��������!");
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
	 * ɾ��ָ���������ƶ���ȫ���ĵ�����Ϣ��
	 * 
	 * @param xc
	 * @param docnames
	 * @return ���������ĵ���ɾ����������ɾ���ɹ�key-value����success�����򷵻�no document
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
	 * ɾ���ƶ������ڵ�ָ���ĵ�����Ϣ
	 * 
	 * @param xc
	 * @param docname
	 * @throws DocumentnotExistException
	 *             �ĵ�������
	 */
	public static void deleteDocument(String dirpath, String docname)
			throws DocumentnotExistException {
		if (isDocumentExist(dirpath, docname)) {
			File file = new File(dirpath + File.separator
					+ getDocumentName(dirpath, docname) + ".xml");
			file.delete();
			EventInfoWriter.deleteDocumentFromEventInfo(docname);
		} else {
			throw new DocumentnotExistException("�¼��洢ϵͳ�����ĵ���" + docname
					+ "������!");
		}

	}

	/**
	 * �����ĵ����ƶ���xmlcontainer������
	 * 
	 * @param xc
	 *            ִ�е�xmlcontainer������
	 * @param docname
	 *            �ĵ�������
	 * @param doccontent
	 *            �ĵ�������
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
			throw new DocumentException("���ݵ�xml�ĵ����ݲ���Ϊ�� !");
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
				// ����ڶ��׶�
				xmltempcontent = EventCommonXMLUtil.insertPreparationPhase(
						xmlcontent, doccontent);
				houzhui = 2;
			} else if (getDocumentName(dirpath, docname).endsWith("_002")) {
				// ��������׶�
				xmlcontent = xmlcontent.replace(
						"<eml:eventPeriod>Preparation</eml:eventPeriod>",
						"<eml:eventPeriod>Response</eml:eventPeriod>");
				xmltempcontent = EventCommonXMLUtil.insertResponsePhase(
						xmlcontent, doccontent);
				houzhui = 3;
			} else if (getDocumentName(dirpath, docname).endsWith("_003")) {
				houzhui = 4;
				// ������Ľ׶�
				xmlcontent = xmlcontent.replace(
						"<eml:eventPeriod>Response</eml:eventPeriod>",
						"<eml:eventPeriod>Recovery</eml:eventPeriod>");
				xmltempcontent = EventCommonXMLUtil.insertRecoveryPhase(
						xmlcontent, doccontent);

			} else {
				throw new XMLDocumentException("����Ϊ" + docname + "��xml�ĵ��Ѿ�����!");
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
			// ɾ�����е�Ҫ��
			for (EventBasicInfo ebi : EventInfoConfig.getEbis()) {
				if (ebi.getDocname().equals(docname)) {
					EventInfoConfig.getEbis().remove(ebi);
					break;
				}
			}
		} else {
			Document xmlcontentdoc = DocumentHelper.parseText(doccontent); // ���ַ���תΪXML
			Element testelement = xmlcontentdoc.getRootElement().element(
					"eventPeriod");
			String houzhui = "_001";
			if (testelement != null) {
				throw new EventBeginException("��������ע���¼���һ�׶�XML�ĵ�!");
			}
			FileOperationUtil.WriteToFileContent(doccontent, dirpath
					+ File.separator + docname + houzhui + ".xml", "UTF-8");

		}
		EventInfoWriter.writeDocument2EventBasicInfo(docname);
		return true;

	}
}
