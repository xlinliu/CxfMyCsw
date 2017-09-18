package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorComputeCapabilityInterface {
	/**
	 * 返回传感器中关于计算能力的信息的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户需要查询的传感器的标示符
	 * @param capability
	 *            用户需要查询的传感器的计算能力的哪方面的能力
	 * 
	 * @return 返回用户指定的传感器的计算能力在capability方面的能力
	 * @throws ServiceException
	 *             返回服务器端缠身的错误信息
	 */
	@WebMethod
	public String GetSensorComputeCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * 返回传感器中关于计算能力的信息的普遍的方法
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户需要查询的传感器的标示符
	 * @param capability
	 *            用户需要查询的传感器的计算能力的哪方面的能力
	 * 
	 * @return 返回用户指定的传感器的计算能力在capability方面的能力
	 * @throws ServiceException
	 *             返回服务器端缠身的错误信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorComputeCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
