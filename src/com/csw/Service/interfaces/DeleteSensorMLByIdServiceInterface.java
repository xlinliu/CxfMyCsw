package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface DeleteSensorMLByIdServiceInterface {
	/**
	 * 删除指定用户指定的sensorml的id
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensormid
	 *            用户的sensomrl的id值
	 * @param deleteType
	 *            删除的类型，取值有:"deleteall","deletesensorml"两者之一
	 * @return 1 删除成功
	 * @throws 抛出异常信息
	 */
	@WebMethod
	public int DeleteSensorMLByIdMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensormid,
			@WebParam(name = "deleteType") String deleteType)
			throws ServiceException;
}
