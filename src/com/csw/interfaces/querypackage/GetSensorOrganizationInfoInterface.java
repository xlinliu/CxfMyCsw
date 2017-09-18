/**
 * 
 */
package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorBasicInfoType;

/**
 * @author Administrator
 * 
 */
@WebService
public interface GetSensorOrganizationInfoInterface {
	/**
	 * 获取传感器的组织信息的方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @return 返回传感器的组织信息（字符串的形式）
	 * @throws ServiceException
	 *             出现问题时返回的信息
	 */
	@WebMethod
	public String GetSensorOrganizationInfoMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器的组织信息的方法
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param sensorid
	 *            需要查询的传感器的标示符
	 * @return 返回传感器的组织信息（字符串的形式）
	 * @throws ServiceException
	 *             出现问题时返回的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorOrganizationInfoForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;

}
