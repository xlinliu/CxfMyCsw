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
public interface GetSensorConnectionEmailInterface {
	/**
	 * 获取传感器联系方式中的email的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的标示符
	 * @return 返回相对应的传感器的管理单位的联系的email的地址
	 * @throws ServiceException
	 *             返回出现的服务问题 详细的信息
	 */
	@WebMethod
	public String GetSensorConnectionEmailMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器联系方式中的email的信息
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorids
	 *            用户查询的传感器的标示符
	 * @return 返回相对应的传感器的管理单位的联系的email的地址
	 * @throws ServiceException
	 *             返回出现的服务问题 详细的信息
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorConnectionEmailForMultiSensorsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") List<String> sensorids)
			throws ServiceException;
}
