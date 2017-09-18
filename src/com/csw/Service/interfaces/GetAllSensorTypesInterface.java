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
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-8-23 ����02:23:32
 */
@WebService
public interface GetAllSensorTypesInterface {
	@WebMethod
	public List<String> getAllSensorTypes(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
