package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorPositionInfoInterface {
	/**
	 * 获取指定的传感器的定位能力的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param infoface
	 *            用户需要查询的定位能力的某一方面
	 * @return 返回查询的结果
	 * @throws ServiceException
	 *             返回服务器端执行时出现的错误的信息
	 */
	@WebMethod
	public String GetSensorPositionInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取指定的传感器的定位能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param infoface
	 *            用户需要查询的定位能力的某一方面
	 * @return 返回查询的结果
	 * @throws ServiceException
	 *             返回服务器端执行时出现的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPositionInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
