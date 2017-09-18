/**
 * 
 */
package com.csw.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *项目名称:CxfMyCsw 类名称AllFileNameOfDirUtil 类描述：获取指定的文件目录下的所有的文件的名称
 * 创建人:Administrator 创建时间: 2013-4-11 下午08:15:07 修改人Administrator
 * 修改时间:2013-4-11下午08:15:07 修改备注 :
 * 
 * @version2013-4-11
 */
public class AllFileNameOfDirUtil {
	/**
	 * 获取指定文件夹中的所有的文件的路径
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
	 * 获取指定文件夹中的所有的文件的路径
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
