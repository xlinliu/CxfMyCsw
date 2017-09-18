/**
 * 
 */
package com.csw.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.csw.utils.FormatXmlUtil;

/**
 *��Ŀ����:CxfMyCsw ������FileContentFormatUtil
 * ���������÷�����Ҫ�����ڽ���Ҫ�ϴ��Ĵ�������sensorML���������ĵ����л������޸� ������:Administrator ����ʱ��: 2013-4-11
 * ����05:39:48 �޸���Administrator �޸�ʱ��:2013-4-11����05:39:48 �޸ı�ע :
 * 
 * @version 2013-4-11
 */
public class FileContentFormatUtil {
	/**
	 * ��Ҫ�滻���ַ�
	 */
	private static String[] replacechars = { "*", "��ʾ����", "��ѡһ��" };

	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
		strs.add("E:\\��ˮ������\\SensorML\\��ˮ�۲����Ǵ�����SML����");
		FileContentFormatMethod(strs);
	}

	/**
	 * �޸��ļ��в���Ҫ����Ϣ
	 * 
	 * @param filenames
	 * @return �����޸Ĺ����ļ����ļ�����
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
						System.out.println(file.getName() + " ���滻");
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
