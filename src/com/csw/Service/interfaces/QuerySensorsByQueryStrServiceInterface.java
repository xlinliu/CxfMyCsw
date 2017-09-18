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
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-8-4 下午12:44:54
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
