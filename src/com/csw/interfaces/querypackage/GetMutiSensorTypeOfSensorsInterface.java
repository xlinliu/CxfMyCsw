/**
 * 
 */
package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw ����������ȡȫ���Ĵ����������� ������:Administrator ����ʱ��: 2013-8-22 ����09:27:55
 */
@WebService
public interface GetMutiSensorTypeOfSensorsInterface {
	/**
	 * ��ȡ����ָ���Ĵ�����������
	 * 
	 * @param sensors
	 * @return
	 */
	@WebMethod
	public List<String> GetMutiSensorTypeOfSensors(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") String sensors) throws ServiceException;
}
