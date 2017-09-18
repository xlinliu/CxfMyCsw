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
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-22 ����07:36:52
 */
public class OperateThemeFileService implements OperateThemeFileInterface {

	public int saveAllThemeFileMethod(List<ThemeFileType> files)
			throws ServiceException {
		if (files != null && files.size() != 0) {
			for (ThemeFileType tft : files) {
				saveThemeFileMethod(tft);
			}
		} else {
			throw new ServiceException("�������ݲ��Ϸ�!");
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
			throw new ServiceException("�����������ò��Ϸ�!");
		}
		FileOutputStream fos;
		InputStream is;
		try {
			String filelocalpath = themefile.getFilelocalpath();
			if (filelocalpath.length() != 0) {
				// ���ļ�·����������/����\
				if (!filelocalpath.endsWith("\\")
						&& !filelocalpath.endsWith("/")) {
					filelocalpath = filelocalpath + "/";
				}
			}
			is = themefile.getImageData().getInputStream();
			String savefilename = filelocalpath + themefile.getFilename() + "."
					+ themefile.getTypename();// �����ͼƬ��λ��
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
			throw new ServiceException("�������ݴ������⣬�����²鿴!");
		}
	}
}
