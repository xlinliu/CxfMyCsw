package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetSensorMLByIdServiceInterface {
	/**
	 * 获取获取的sensor的xml文档的内容，这个是特定的上传的用户所提供的
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param docId
	 *            需要查询的sensorml的id值，这个值可以是sensorml的id值也可以是转换后的ebrim的id值
	 * 
	 * @return 返回的是 特定的用户和特定的id的sensorml的文档的内容
	 */
	@WebMethod
	public List<String> GetSensorMLByIdServiceMehtod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "docId") String docId) throws ServiceException;
}
