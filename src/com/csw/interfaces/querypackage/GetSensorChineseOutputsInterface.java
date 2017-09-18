/**
 * 
 */
package com.csw.interfaces.querypackage;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.csw.exceptions.ServiceException;
import com.csw.utils.custometypes.SensorOutputType;

/**
 *项目名称:CxfMyCsw 类描述： 创建人:Administrator 创建时间: 2013-10-23 上午09:19:35
 */
@WebService
public interface GetSensorChineseOutputsInterface {
	/**
	 * 获取传感器的中文输出
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            用户密码
	 * @param sensorid
	 *            传感器标识符
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public SensorOutputType GetSensorChineseOutputInfo(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorid") String sensorid)
			throws ServiceException;

	/**
	 * 获取多个传感器的中文输出
	 * 
	 * @param username
	 * @param password
	 * @param sensorids
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod
	public List<SensorOutputType> GetSensorChineseOutputInfoList(
			@WebParam(name = "username") String username,
			@WebParam(name = "password") String password,
			@WebParam(name = "sensorids") List<String> sensorids)
			throws ServiceException;
}
