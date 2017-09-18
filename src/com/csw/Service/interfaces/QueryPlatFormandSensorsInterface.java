/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.PlatformandSensors;

/**
 *��Ŀ����:CxfMyCsw ����������ѯע�������е����е�ƽ̨���丽���Ĵ��������б� ������:Administrator ����ʱ��: 2013-8-23
 * ����09:10:37
 */
@WebService
public interface QueryPlatFormandSensorsInterface {
	/**
	 * ��ȡϵͳ�д洢�����д�������ƽ̨���丽���Ĵ������ı�ʶ�����б�
	 * 
	 * @param username
	 *            �û�����
	 * @param password
	 *            �û�����
	 * @param platfromtype
	 *            ƽ̨������
	 * @return ���ص��Ǵ�������ƽ̨������صĴ��������б����ڶ�����ԡ�|������
	 */
	@WebMethod
	public List<PlatformandSensors> GetPlatFormandSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "platformtype") String platfromtype)
			throws ServiceException;
}
