package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetOwnerAllSensorMLDocumentServiceInterface {
	/**
	 * 获取所有的属UserName传递上来的SensorML的文档内容的集合,返回的是
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @return 返回所有属于UserName的SensorML的文档内容的集合
	 */
	@WebMethod
	public List<String> GetOwnerAllSensorMLDocumentMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password)
			throws ServiceException;
}
