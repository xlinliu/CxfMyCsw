package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;

@WebService
public interface GetAllIdsOfSensormlServiceInterface {
	/**
	 * 获取指定的用户在注册中心所存储的全部的sensorml的id值的集合
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param type
	 *            返回的id值得形式，是sensorml格式的，还是ebrim格式的
	 * @return 返回所有
	 *         username的所存储的sensorml的id值的集合，这里返回的是sensorml形式的id值，为空或者发生异常返回null
	 */
	@WebMethod
	public List<String> GetAllIdsOfSensormlMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "type") String type) throws ServiceException;
}
