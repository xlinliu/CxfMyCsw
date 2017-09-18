/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBBox;

/**
 *��Ŀ����:CxfMyCsw �������� ������:Administrator ����ʱ��: 2013-10-14 ����01:10:48
 */
@WebService
public interface QueryAllSensorsBBOXInterface {
	@WebMethod
	public List<SensorBBox> QuerySensorsBBOXMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensors") List<String> sensors)
			throws ServiceException;
}
