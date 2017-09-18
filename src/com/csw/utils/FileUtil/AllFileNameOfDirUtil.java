/**
 * 
 */
package com.csw.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *��Ŀ����:CxfMyCsw ������AllFileNameOfDirUtil ����������ȡָ�����ļ�Ŀ¼�µ����е��ļ�������
 * ������:Administrator ����ʱ��: 2013-4-11 ����08:15:07 �޸���Administrator
 * �޸�ʱ��:2013-4-11����08:15:07 �޸ı�ע :
 * 
 * @version2013-4-11
 */
public class AllFileNameOfDirUtil {
	/**
	 * ��ȡָ���ļ����е����е��ļ���·��
	 * 
	 * @param filename
	 * @return
	 */
	public static List<String> GetAllFileNameOfDirMethod(String filename) {
		List<String> result = new ArrayList<String>();
		if (filename == null || filename.trim().equals("")) {
			return null;
		}
		File file = new File(filename);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				List<String> tempList = GetAllFileNameOfDirMethod(f
						.getAbsolutePath());
				if (tempList != null && tempList.size() != 0) {
					result.addAll(tempList);
				}
			}
		} else {
			result.add(file.getAbsolutePath());
		}
		return result;
	}

	/**
	 * ��ȡָ���ļ����е����е��ļ���·��
	 * 
	 * @param filename
	 * @return
	 */
	public static List<String> GetAllFileShortNameOfDirMethod(String filename) {
		List<String> result = new ArrayList<String>();
		if (filename == null || filename.trim().equals("")) {
			return null;
		}
		File file = new File(filename);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				List<String> tempList = GetAllFileShortNameOfDirMethod(f
						.getAbsolutePath());
				if (tempList != null && tempList.size() != 0) {
					result.addAll(tempList);
				}
			}
		} else {
			result.add(file.getName());
		}
		return result;
	}

	public static String ReplaceFileNameWith_(String filename) {
		return filename.replaceAll(":", "_");
	}
}
