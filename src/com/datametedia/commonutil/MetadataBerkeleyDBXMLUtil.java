package com.datametedia.commonutil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csw.exceptions.ServiceException;
import com.csw.utils.FileUtil.FileOperationUtil;
import com.model.commonutil.ModelInfoWriter;
import com.csw.exceptions.DirectoryNotExistException;
import com.csw.exceptions.DocumentnotExistException;
import com.csw.exceptions.XMLDocumentException;
import com.csw.exceptions.XMLDocumentNotExistException;

/**
 * berkeley db xml数据库建设
 * 
 * @author yxliang
 * 
 */
public class MetadataBerkeleyDBXMLUtil {
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
			for (int i = 0; i < len; i++) {
				result[i] = tt[i].substring(0, tt[i].length() - 4);
			}
			return result;
		} else {
			throw new DirectoryNotExistException("元数据存储系统路径不存在!");
		}
	}

	/**
	 * 判断文档是否存在
	 * 
	 * @param dirpath
	 *            文档的存储路径
	 * @param docname
	 *            需要检索的文档的名称
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
			throw new DocumentnotExistException("元数据存储系统中文档【" + docname
					+ "】不存在!");
		}
		try {
			String recontent = FileOperationUtil.ReadFileContent(dirpath
					+ File.separator + docname + ".xml", "UTF-8");
			// String fileString =
			// "<geodataCoreMeta xsi:schemaLocation=\"http://www.geodata.cn/metadata/GeodataCore http://www.geodata.cn/Portal/metadata/docs/schemas/GeoCore2.xsd\" xmlns=\"http://www.geodata.cn/metadata/GeodataCore\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
			// String temp = "<geodataCoreMeta>";
			// recontent = recontent.replace(temp, fileString);
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
	 * @throws XMLDocumentNotExistException
	 * @throws DocumentnotExistException
	 */
	public static Map<String, String> deleteDocument(String dirpath,
			List<String> docnames) throws XMLDocumentNotExistException {
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
	 * @throws XmlException
	 * @throws XMLDocumentNotExistException
	 *             文档不存在
	 * @throws DocumentnotExistException
	 */
	public static void deleteDocument(String dirpath, String docname)
			throws XMLDocumentNotExistException, DocumentnotExistException {
		if (isDocumentExist(dirpath, docname)) {
			File file = new File(dirpath + File.separator + docname + ".xml");
			file.delete();
			ModelInfoWriter.deleteDocumentFromModelBasicInfo(docname);
			MetadataInfoWriter.deleteForMetadataConfig(docname);
		} else {
			throw new DocumentnotExistException("元数据存储系统中无文档【" + docname
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
	 * @throws ServiceException
	 */
	public static Boolean putDocument(String dirpath, String docname,
			String doccontent) throws XMLDocumentException, ServiceException {
		if (isDocumentExist(dirpath, docname)) {
			throw new XMLDocumentException("名称为" + docname + "的xml文档已经存在!");
		} else {
			if (doccontent == null || doccontent.equals("")
					|| doccontent.trim().equals("")) {
				throw new ServiceException("注册不能为空!");
			}
			try {
				doccontent = new String(doccontent.getBytes(), "GBK").replace(
						"?<?", "<?");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String fileString = "<geodataCoreMeta xsi:schemaLocation=\"http://www.geodata.cn/metadata/GeodataCore http://www.geodata.cn/Portal/metadata/docs/schemas/GeoCore2.xsd\" xmlns=\"http://www.geodata.cn/metadata/GeodataCore\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
			String temp = "<geodataCoreMeta>";
			String tempdoc = doccontent.replace(fileString, temp);
			MetedataXMLDBUtil.isformatdocument(tempdoc);
			FileOperationUtil.WriteToFileContent(doccontent, dirpath
					+ File.separator + docname + ".xml", "UTF-8");
		}
		// 将数据写入进去
		MetadataInfoWriter.write2MetadataBasicInfo(docname);
		return true;
	}
}
