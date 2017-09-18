package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface TransformSensorMLToebRIMServiceInterface {
	/**
	 * 将sensorml转换为ebrim的功能
	 * 
	 * @param username
	 *            用户姓名
	 * @param password
	 *            用户密码
	 * @param sensorml
	 *            sensorml的内容
	 * @return 成功返回ebrim内容，失败返回null，异常返回null
	 */
	@WebMethod
	public String TransactionSensorMLToeEbRIMMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)
			throws ServiceException;
}
