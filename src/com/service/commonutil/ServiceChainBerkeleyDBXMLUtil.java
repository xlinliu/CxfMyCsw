package com.service.commonutil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.DocumentException;
import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

/**
 * berkeley db xml���ݿ⽨��
 * 
 * @author yxliang
 * 
 */
public class ServiceChainBerkeleyDBXMLUtil {
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
	 * ��ȡָ���洢ϵͳ�е�ȫ���ļ�����
	 * 
	 * @param dirpath
	 * @return
	 * @throws DirectoryNotExistException
	 */
	public static String[] getAllDocumentName(String dirpath)
			throws DirectoryNotExistException {
		File file = new File(dirpath);
		List<String> strings = new ArrayList<String>();
		if (file.exists() && file.isDirectory()) {
			for (String str : file.list()) {
				strings.add(str.substring(0, str.length() - 4));
			}
			String[] strings2 = new String[strings.size()];
			for (int i = 0; i < strings.size(); i++) {
				strings2[i] = strings.get(i);
			}
			return strings2;
		} else {
			throw new DirectoryNotExistException("�������洢ϵͳ·��������!");
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
			throw new DocumentnotExistException("�������洢ϵͳ���ĵ���" + docname
					+ "��������!");
		}
		try {
			String recontent = FileOperationUtil.ReadFileContent(dirpath
					+ File.separator + docname + ".xml", "UTF-8");
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
	 * @throws DocumentnotExistException
	 */
	public static Map<String, String> deleteDocument(String dirpath,
			List<String> docnames) throws DocumentnotExistException {
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
	 * @throws XmlException
	 * @throws XMLDocumentNotExistException
	 *             �ĵ�������
	 * @throws DocumentnotExistException
	 */
	public static void deleteDocument(String dirpath, String docname)
			throws DocumentnotExistException {
		if (isDocumentExist(dirpath, docname)) {
			File file = new File(dirpath + File.separator + docname + ".xml");
			file.delete();
			ServiceChainlInfoWriter.deleteDocumentFromServiceChainInfo(docname);
		} else {
			throw new DocumentnotExistException("�������洢ϵͳ�����ĵ���" + docname
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
	 * @throws DocumentException
	 * @throws ServiceException
	 */
	public static Boolean putDocument(String dirpath, String docname,
			String doccontent) throws XMLDocumentException, DocumentException,
			ServiceException {
		if (isDocumentExist(dirpath, docname)) {
			throw new XMLDocumentException("����Ϊ" + docname + "��xml�ĵ��Ѿ�����!");
		} else {
			if (doccontent == null || doccontent.equals("")
					|| doccontent.trim().equals("")) {
				throw new ServiceException("�ĵ�����Ϊ��!");
			}
			try {
				doccontent = new String(doccontent.getBytes(), "GBK").replace(
						"?<?", "?");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			ServiceChainXMLDBUtil.isformatdocument(doccontent);
			FileOperationUtil.WriteToFileContent(doccontent, dirpath
					+ File.separator + docname + ".xml", "UTF-8");
		}
		// ModelInfoWriter.writeDocument2ModelBasicInfo(docname);
		ServiceChainlInfoWriter.writeDocument2ServiceChainInfo(docname);
		return true;
	}
}
