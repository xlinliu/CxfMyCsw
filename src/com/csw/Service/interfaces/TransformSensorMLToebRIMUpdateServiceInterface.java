package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface TransformSensorMLToebRIMUpdateServiceInterface {
	/**
	 * 将sensorml内容转换为ebrim并保存起来
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorml
	 *            需要转换的transformsensorml
	 * @return 转换保存成功返回 ebrim格式文档内容，失败返回 null，发生异常返回null
	 */
	@WebMethod
	public String TransformSnesorMLToebRIMUpdateMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)throws ServiceException;
}
