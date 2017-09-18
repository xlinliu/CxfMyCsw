/**
 * 
 */
package com.zx.Service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.csw.exceptions.ServiceException;
import com.zx.Service.interfaces.OperateThemeFileInterface;
import com.zx.Service.interfaces.ThemeFileType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-22 下午07:36:52
 */
public class OperateThemeFileService implements OperateThemeFileInterface {

	public int saveAllThemeFileMethod(List<ThemeFileType> files)
			throws ServiceException {
		if (files != null && files.size() != 0) {
			for (ThemeFileType tft : files) {
				saveThemeFileMethod(tft);
			}
		} else {
			throw new ServiceException("传送数据不合法!");
		}
		return 1;
	}

	public int saveThemeFileMethod(ThemeFileType themefile)
			throws ServiceException {
		if (themefile == null || themefile.getFilelocalpath() == null
				|| themefile.getFilewebpath() == null
				|| themefile.getFilename() == null
				|| themefile.getTypename() == null
				|| themefile.getImageData() == null) {
			throw new ServiceException("传送数据设置不合法!");
		}
		FileOutputStream fos;
		InputStream is;
		try {
			String filelocalpath = themefile.getFilelocalpath();
			if (filelocalpath.length() != 0) {
				// 在文件路径后面增加/或者\
				if (!filelocalpath.endsWith("\\")
						&& !filelocalpath.endsWith("/")) {
					filelocalpath = filelocalpath + "/";
				}
			}
			is = themefile.getImageData().getInputStream();
			String savefilename = filelocalpath + themefile.getFilename() + "."
					+ themefile.getTypename();// 保存的图片的位置
			fos = new FileOutputStream(savefilename);

			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				fos.write(bytes, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("传送数据存在问题，请重新查看!");
		}
	}
}
