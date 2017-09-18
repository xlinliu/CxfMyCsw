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
 *项目名称:CxfMyCsw 类名称MutilFileInputDBUtil 类描述：将所有的传感器的信息文件进行处理 创建人:Administrator
 * 创建时间: 2013-4-11 下午07:58:53 修改人Administrator 修改时间:2013-4-11下午07:58:53 修改备注 :
 * 
 * @version 2013-4-11
 */
public class MutilFileInputDBUtil {
	static String basepath = "E:\\洪水的数据\\SensorML\\洪水观测卫星传感器SML汇总";

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
	 * 将需要上传的传感器的信息上传到数据库中
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
			// 读取传感器的senosrml的文档的信息
			String filecontent = FileOperationUtil.ReadFileContent(filename,
					"UTF-8");
			try {
				// 转换ebrim格式
				new TransformSensorMLToebRIMService()
						.TransactionSensorMLToeEbRIMMethod("admin", "cswadmin",
								filecontent.trim());
				// 保存
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
