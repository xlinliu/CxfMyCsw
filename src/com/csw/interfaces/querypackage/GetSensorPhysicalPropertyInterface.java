package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorPhysicalPropertyInterface {
	/**
	 * 获取传感器的物理属性的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的物理属性的传感器的标识符
	 * @param physicalproperty
	 *            需要查询的传感器的物理属性的信息
	 * @return 返回传感器的physicalproperty的属性信息
	 * @throws ServiceException
	 *             返回服务器中发生的服务错误的信息
	 */
	@WebMethod
	public String GetSensorPhysicalPropertyMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;

	/**
	 * 获取传感器的物理属性的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的物理属性的传感器的标识符
	 * @param physicalproperty
	 *            需要查询的传感器的物理属性的信息
	 * @return 返回传感器的physicalproperty的属性信息
	 * @throws ServiceException
	 *             返回服务器中发生的服务错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorPhysicalPropertyForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "physicalproperty") String physicalproperty)
			throws ServiceException;
}
