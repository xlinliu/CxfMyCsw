package com.zx.Service.interfaces;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw �����������ڱ����SensorMLͬʱ�����������ļ� ������:Administrator ����ʱ��: 2013-9-6
 * ����08:48:37
 */
@WebService
public interface OperateThemeFileInterface {
	/**
	 *����ר��ͼ�ļ�
	 * 
	 * @param filename
	 *            ר��ͼ�ļ�����
	 * @param attchment
	 *            �û��ϴ����ļ�
	 * @return �����Ƿ�ɹ���1���ɹ���2��ʧ��
	 */
	@WebMethod
	public int saveThemeFileMethod(
			@WebParam(name = "attchment") ThemeFileType image)
			throws ServiceException;

	/**
	 * ����ר��ͼ�ļ�����
	 * 
	 * @param files
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public int saveAllThemeFileMethod(
			@WebParam(name = "files") List<ThemeFileType> files)
			throws ServiceException;
}