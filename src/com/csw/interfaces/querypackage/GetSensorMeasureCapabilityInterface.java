package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorMeasureCapabilityInterface {

	/**
	 * 获取传感器的某一方面的测量能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的哪方面的能力
	 * @return 返回制定的传感器在该capability方面的能力的信息
	 * @throws ServiceException
	 *             返回服务器端发生的服务错误的信息
	 */
	@WebMethod
	public String GetSensorMeasureCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * 获取传感器的某一方面的测量能力
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的哪方面的能力
	 * @return 返回制定的传感器在该capability方面的能力的信息
	 * @throws ServiceException
	 *             返回服务器端发生的服务错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorMeasureCapabilitiesForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
