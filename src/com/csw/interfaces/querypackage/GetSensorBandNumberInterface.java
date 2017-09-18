package com.csw.interfaces.querypackage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorBandNumberInterface {
	/**
	 * 获取传感器的波段数的信息
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            用户需要查询获取的波段数的信息的传感器的标识符
	 * @return 返回传感器的波段数的信息
	 * @throws ServiceException
	 *             返回服务器端的错误信息
	 */
	@WebMethod
	public String GetSensorBandNumberMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
