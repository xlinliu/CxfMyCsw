package com.csw.Service.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSimpleSensorServiceInterface {
	/**
	 *删除指定的RegistryPackage的id值，
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            删除的SensorId，可以是sensorML格式的，也可以是ebRIM格式的
	 * @return 删除成功返回1，删除失败返回0 ，删除出现异常返回2
	 */
	public int DeleteSimpleSensorMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;
}
