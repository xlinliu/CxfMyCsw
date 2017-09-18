package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorWorkEndTimeInterface {
	/**
	 * 获取传感器的工作结束时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作结束时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public String GetSensorWorkValidTimeEndMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器的工作结束时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作结束时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeEndForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
