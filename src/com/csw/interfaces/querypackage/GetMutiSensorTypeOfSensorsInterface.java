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
 *项目名称:CxfMyCsw 类描述：获取全部的传感器的类型 创建人:Administrator 创建时间: 2013-8-22 上午09:27:55
 */
@WebService
public interface GetMutiSensorTypeOfSensorsInterface {
	/**
	 * 获取所有指定的传感器的类型
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
