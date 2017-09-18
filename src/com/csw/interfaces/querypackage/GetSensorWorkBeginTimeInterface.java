package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorWorkBeginTimeInterface {
	/**
	 * 获取传感器的工作开始时间信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回传感器的工作开始时间
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public String GetSensorWorkValidTimeBeginMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器集合中工作开始时间信息集合
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符集合
	 * @return 返回传感器的工作开始时间集合
	 * @throws ServiceException
	 *             返回服务器端出现的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorWorkValidTimeBeginForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
