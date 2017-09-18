/**
 * 
 */
package com.csw.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.csw.Service.impls.SaveEbRimService;
import com.csw.Service.impls.TransformSensorMLToebRIMService;
import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw ������MutilFileInputDBUtil �������������еĴ���������Ϣ�ļ����д��� ������:Administrator
 * ����ʱ��: 2013-4-11 ����07:58:53 �޸���Administrator �޸�ʱ��:2013-4-11����07:58:53 �޸ı�ע :
 * 
 * @version 2013-4-11
 */
public class MutilFileInputDBUtil {
	static String basepath = "E:\\��ˮ������\\SensorML\\��ˮ�۲����Ǵ�����SML����";

	public static void main(String[] args) {
		File file = new File(basepath);
		List<String> files = AllFileNameOfDirUtil
				.GetAllFileNameOfDirMethod(file.getAbsolutePath());
		MutilFileInputDBUtil ft = new MutilFileInputDBUtil();
		List<String> filess = ft.TiJiaoFilesToDB(files);
		for (String fi : filess) {
			System.out.println(fi);
		}
	}

	/**
	 * ����Ҫ�ϴ��Ĵ���������Ϣ�ϴ������ݿ���
	 * 
	 * @param filenames
	 * @return
	 */
	public List<String> TiJiaoFilesToDB(List<String> filenames) {
		List<String> failedfiles = new ArrayList<String>();
		if (filenames == null || filenames.size() == 0) {
			return null;
		}
		for (String filename : filenames) {
			// ��ȡ��������senosrml���ĵ�����Ϣ
			String filecontent = FileOperationUtil.ReadFileContent(filename,
					"UTF-8");
			try {
				// ת��ebrim��ʽ
				new TransformSensorMLToebRIMService()
						.TransactionSensorMLToeEbRIMMethod("admin", "cswadmin",
								filecontent.trim());
				// ����
				System.out.println(new SaveEbRimService().SaveEbRIMMethod(
						"admin", "cswadmin", null, null, filecontent, null));
			} catch (ServiceException e) {
				e.printStackTrace();
				failedfiles.add(filename);
			}
		}
		return failedfiles;
	}
}
