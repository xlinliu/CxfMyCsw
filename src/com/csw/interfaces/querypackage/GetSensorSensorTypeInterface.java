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
public interface GetSensorSensorTypeInterface {
	/**
	 * 获取传感器的类型，如雷达型，摄影型，地面站点等
	 * 
	 * @param username
	 *            用户的名称
	 * @param password
	 *            用户的密码
	 * @param sensorid
	 *            用户查询的传感器的id值
	 * @return 返回相对应的传感器的类型，如雷达型，摄影型，地面站典型，原位传感器等。
	 * @throws ServiceException
	 *             返回失败的消息
	 */
	@WebMethod
	public String GetSensorSensorTypeMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取传感器的类型，如雷达型，摄影型，地面站点等(集合层次操作）
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符
	 * @return 返回
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorBasicInfoType> GetSensorSensorTypeForMultiSensorMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
