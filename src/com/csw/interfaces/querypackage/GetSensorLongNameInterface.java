package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

@WebService
public interface GetSensorLongNameInterface {
	/**
	 * 获取指定传感器的全称
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 返回传感器的全称
	 */
	@WebMethod(operationName = "GetSensorLongNameMethod")
	public String GetSensorLongNameMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") String sensorid)
			throws ServiceException;

	/**
	 * 获取指定的传感器序列的集合
	 * 
	 * @param username
	 * @param password
	 * @param sensorid
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorLongNamesMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorid)
			throws ServiceException;
}
