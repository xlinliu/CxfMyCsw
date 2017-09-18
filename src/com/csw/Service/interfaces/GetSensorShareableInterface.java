/**
 * 
 */
package com.csw.Service.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorShareLevel;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-15 下午04:46:07
 */
@WebService
public interface GetSensorShareableInterface {
	/**
	 * 传感器可共享查询方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return 传感器共享信息
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorShareLevel GetSensorShareLevelMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 传感器可共享查询方法
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorids
	 *            传感器标识符集合
	 * @return 传感器共享信息集合
	 * @throws ServiceException
	 */
	public List<SensorShareLevel> GetSensorShareLevelsMethod(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
