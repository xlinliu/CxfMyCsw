/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-4 ����12:44:54
 */
@WebService
public interface QuerySensorsByQueryStrServiceInterface {
	@WebMethod
	public List<String> GetSensorsIdByQueryStr(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "conditionStr") String conditionStr)
			throws ServiceException;
}
