package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorCommunicationCapabilityInterface {
	/**
	 * 查询传感器中通信能力的
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的通信能力的哪一方面
	 * @return 返回传感器的查询的某一方面的通信能力的详细的信息
	 * @throws ServiceException
	 *             返回传感器中的发生的错误的信息
	 */
	@WebMethod
	public String GetSensorCommunicationCapabilityMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid,
			@WebParam(name = "capability") String capability)
			throws ServiceException;

	/**
	 * 查询传感器中通信能力的
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            需要查询的传感器的标示符
	 * @param capability
	 *            用户查询的传感器的通信能力的哪一方面
	 * @return 返回传感器的查询的某一方面的通信能力的详细的信息
	 * @throws ServiceException
	 *             返回传感器中的发生的错误的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorCommunicationCapabilityForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids,
			@WebParam(name = "capability") String capability)
			throws ServiceException;
}
