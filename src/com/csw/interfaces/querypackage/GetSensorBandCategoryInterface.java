package com.csw.interfaces.querypackage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorBandCategoryInterface {
	/**
	 * 获取传感器的波段类别
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询波段类别的传感器
	 * @return 返回传感器的波段类别的信息
	 * @throws ServiceException
	 *             返回服务器产生的错误信息
	 */
	@WebMethod
	public String GetSensorBandCategoryMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
