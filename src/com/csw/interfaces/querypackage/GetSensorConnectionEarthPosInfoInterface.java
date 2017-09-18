package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorConnectionEarthPosInfoInterface {
	/**
	 * 获取传感器的组织单位的具体地址的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标识符
	 * @return 返回所需要的传感器管理的单位的具体的地址的信息
	 * @throws ServiceException
	 *             返回出现的错误的信息
	 */
	@WebMethod
	public String GetSensorConnectionEarthPosInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器的组织单位的具体地址的信息的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标识符
	 * @return 返回所需要的传感器管理的单位的具体的地址的信息
	 * @throws ServiceException
	 *             返回出现的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEarthPosInfoFroMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;

}
