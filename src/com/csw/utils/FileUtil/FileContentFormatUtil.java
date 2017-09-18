/**
 * 
 */
package com.csw.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.csw.utils.FormatXmlUtil;

/**
 *项目名称:CxfMyCsw 类名称FileContentFormatUtil
 * 类描述：该方法主要是用于将需要上传的传感器的sensorML的描述的文档进行基本的修改 创建人:Administrator 创建时间: 2013-4-11
 * 下午05:39:48 修改人Administrator 修改时间:2013-4-11下午05:39:48 修改备注 :
 * 
 * @version 2013-4-11
 */
public class FileContentFormatUtil {
	/**
	 * 需要替换的字符
	 */
	private static String[] replacechars = { "*", "（示例）", "（选一）" };

	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
		strs.add("E:\\洪水的数据\\SensorML\\洪水观测卫星传感器SML汇总");
		FileContentFormatMethod(strs);
	}

	/**
	 * 修改文件中不需要的信息
	 * 
	 * @param filenames
	 * @return 返回修改过的文件的文件名称
	 */
	public static List<String> FileContentFormatMethod(List<String> filenames) {
		List<String> resultfiles = new ArrayList<String>();
		if (replacechars.length == 0) {
			return null;
		}
		for (String str : filenames) {
			File file = new File(str);
			// System.out.println(file.getAbsolutePath());
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					List<String> files = new ArrayList<String>();
					files.add(f.getAbsolutePath());
					resultfiles.addAll(FileContentFormatMethod(files));
				}
			} else {
				String filecontent = FileOperationUtil
						.ReadFileContentByFile(file);
				for (int i = 0; i < replacechars.length; i++) {
					if (filecontent.contains(replacechars[i])) {
						resultfiles.add(file.getName());
						while (filecontent.contains(replacechars[i])) {
							filecontent = filecontent.replace(replacechars[i],
									"");
						}
					} else {
						System.out.println(file.getName() + " 无替换");
					}
				}
				FileOperationUtil.WriteToFileContent(FormatXmlUtil
						.formatXml(filecontent), file.getAbsolutePath(),
						"UTF-8");
			}
		}
		return resultfiles;
	}
}
