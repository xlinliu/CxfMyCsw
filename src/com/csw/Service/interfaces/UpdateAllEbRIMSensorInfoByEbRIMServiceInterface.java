package com.csw.Service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface UpdateAllEbRIMSensorInfoByEbRIMServiceInterface {
	/**
	 * 通过SensorML更新sensor的功能
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorml
	 *            需要转换的sensorml的文档的内容
	 * @return 成功返回ebrim格式的文档内容，失败返回null，发生异常返回null
	 */
	@WebMethod
	public String UpdateAllEbRIMSensorInfoByEbRIMMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorml") String sensorml)throws ServiceException;
}
