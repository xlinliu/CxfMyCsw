package com.csw.Service.interfaces;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorandBelongPlatform;

/**
 *��Ŀ����:CxfMyCsw ����������ȡ������������ƽ̨ ������:Administrator ����ʱ��: 2013-8-23 ����01:26:53
 */
@WebService
public interface GetSensorBelongPlatformInterface {
	/**
	 * ��ȡ��������ȫ��
	 * 
	 * @param username
	 *            ע����������
	 * @param password
	 *            ע����������
	 * @param sensors
	 *            ����������
	 * @return �������д��������е���ϢSensorandBelongPlatform����
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorandBelongPlatform> GetSensorsBelongPlatform(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException;

}
